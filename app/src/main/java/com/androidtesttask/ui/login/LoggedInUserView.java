package com.androidtesttask.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String code;

    LoggedInUserView(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }
}