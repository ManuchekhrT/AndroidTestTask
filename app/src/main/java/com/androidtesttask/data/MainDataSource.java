package com.androidtesttask.data;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.androidtesttask.data.model.Data;
import com.androidtesttask.data.model.LoggedIn;
import com.androidtesttask.data.model.LoggedInData;
import com.androidtesttask.data.model.LoggedInPicture;
import com.androidtesttask.ui.login.LoginRequest;
import com.androidtesttask.ui.main.ImageRequest;
import com.androidtesttask.ui.main.MainRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainDataSource {

    public Result<LoggedInData> getData(String code, int p) {
        try {
            String result = new MainRequest(code, String.valueOf(p)).execute().get();
            JSONObject response = new JSONObject(result);
            String status = response.getString("status");
            int page = response.getInt("page");

            List<Data> dataList =new ArrayList<>();
            JSONArray jsonArray = response.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String country = jsonObject.getString("country");
                Double lat = jsonObject.getDouble("lat");
                Double lon = jsonObject.getDouble("lon");
                Data data = new Data(id, name, country, lat, lon);
                dataList.add(data);
            }

            LoggedInData loggedIn = new LoggedInData(status, page, dataList);
            if (status.equalsIgnoreCase("ok")) {
                return new Result.Success<LoggedInData>(loggedIn);
            } else
                return new Result.UnSuccess<LoggedInData>(loggedIn);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error getting data", e));
        }
    }

    public Result<LoggedInPicture> getPicture() {
        try {
            Bitmap result = new ImageRequest("https://api.androidhive.info/images/sample.jpg").execute().get();
            LoggedInPicture loggedInPicture = new LoggedInPicture(result);
            return new Result.Success<LoggedInPicture>(loggedInPicture);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

}
