package com.androidtesttask.ui.login;

import android.os.AsyncTask;

import com.androidtesttask.http.HttpHandler;


public class LoginRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String url = "https://www.alarstudios.com/test/auth.cgi";
        String param0 = params[0];
        String param1 = params[1];
        String currUrl = url + "?username=" + param0 + "&password=" + param1;
        HttpHandler handler = new HttpHandler();
        return handler.makeServiceCall(currUrl);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}