package com.androidtesttask.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageRequest extends AsyncTask<Void, Void, Bitmap> {

    private String imageUrl;

    public ImageRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

}
