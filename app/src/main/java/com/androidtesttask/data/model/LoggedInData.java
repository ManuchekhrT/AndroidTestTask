package com.androidtesttask.data.model;

import java.util.List;

public class LoggedInData {
    private String status;
    private int page;
    private List<Data> dataList;

    public LoggedInData(String status, int page, List<Data> data) {
        this.status = status;
        this.page = page;
        this.dataList = data;
    }

    public List<Data> getDataList() {
        return dataList;
    }
}
