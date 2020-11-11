package com.androidtesttask.data;

import com.androidtesttask.data.model.LoggedIn;
import com.androidtesttask.ui.login.LoginRequest;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedIn> login(String username, String password) {
        try {
            String result = new LoginRequest().execute(username, password).get();
            JSONObject response = new JSONObject(result);
            String status = response.getString("status");
            String code = response.getString("code");

            LoggedIn loggedIn = new LoggedIn(code, status);
            if (status.equalsIgnoreCase("ok")) {
                return new Result.Success<LoggedIn>(loggedIn);
            } else
                return new Result.UnSuccess<LoggedIn>(loggedIn);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {

    }
}

