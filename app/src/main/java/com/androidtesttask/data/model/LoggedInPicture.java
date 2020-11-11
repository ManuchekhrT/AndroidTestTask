package com.androidtesttask.data.model;

import android.graphics.Bitmap;

public class LoggedInPicture {
    private Bitmap picture;

    public Bitmap getPicture() {
        return picture;
    }

    public LoggedInPicture(Bitmap picture) {
        this.picture = picture;
    }
}
