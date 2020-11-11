package com.androidtesttask.data;

import com.androidtesttask.data.model.LoggedInData;
import com.androidtesttask.data.model.LoggedInPicture;

public class MainRepository {

    private static volatile MainRepository instance;

    private MainDataSource dataSource;

    private LoggedInData data = null;
    private LoggedInPicture picture = null;


    // private constructor : singleton access
    private MainRepository(MainDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static MainRepository getInstance(MainDataSource dataSource) {
        if (instance == null) {
            instance = new MainRepository(dataSource);
        }
        return instance;
    }

    public Result<LoggedInData> getData(String code, int page) {
        Result<LoggedInData> result = dataSource.getData(code, page);
        if (result instanceof Result.Success) {
            setLoggedInData(((Result.Success<LoggedInData>) result).getData());
        }
        if (result instanceof Result.UnSuccess) {
            setLoggedInData(((Result.UnSuccess<LoggedInData>) result).getData());
        }
        return result;
    }

    public Result<LoggedInPicture> getPicture() {
        Result<LoggedInPicture> result = dataSource.getPicture();
        if (result instanceof Result.Success) {
            setLoggedInPicture(((Result.Success<LoggedInPicture>) result).getData());
        }
        if (result instanceof Result.UnSuccess) {
            setLoggedInPicture(((Result.UnSuccess<LoggedInPicture>) result).getData());
        }
        return result;
    }


    private void setLoggedInData(LoggedInData data) {
        this.data = data;
    }

    private void setLoggedInPicture(LoggedInPicture picture) {
        this.picture = picture;
    }

}
