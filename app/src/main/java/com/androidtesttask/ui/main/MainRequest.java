package com.androidtesttask.ui.main;

import android.os.AsyncTask;
import com.androidtesttask.http.HttpHandler;


public class MainRequest extends AsyncTask<String, Void, String> {

    private String code;
    private String page;

    public MainRequest(String code, String page) {
        this.code = code;
        this.page = page;
    }

    @Override
    protected String doInBackground(String... params){
        String url = "https://www.alarstudios.com/test/data.cgi";
        String currUrl = url + "?code=" + code.trim() + "&p=" + page.trim();
        HttpHandler httpHandler = new HttpHandler();
        return httpHandler.makeServiceCall(currUrl);
    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}