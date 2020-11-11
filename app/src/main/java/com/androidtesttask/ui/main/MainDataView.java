package com.androidtesttask.ui.main;

import android.graphics.Bitmap;

import com.androidtesttask.data.model.Data;

import java.util.List;

public class MainDataView {
    private List<Data> dataList;
    private Bitmap picture;

    MainDataView(List<Data> dataList) {
        this.dataList = dataList;
    }

    List<Data> getDataList() {
        return dataList;
    }
}
