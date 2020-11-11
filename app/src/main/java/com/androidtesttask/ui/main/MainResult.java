package com.androidtesttask.ui.main;


import androidx.annotation.Nullable;

public class MainResult {
    @Nullable
    private MainDataView success;

    @Nullable
    private Integer error;

    MainResult(@Nullable Integer error) {
        this.error = error;
    }

    MainResult(@Nullable MainDataView success) {
        this.success = success;
    }

    @Nullable
    MainDataView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
