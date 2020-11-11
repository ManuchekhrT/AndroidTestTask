package com.androidtesttask.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidtesttask.R;
import com.androidtesttask.data.MainRepository;
import com.androidtesttask.data.Result;
import com.androidtesttask.data.model.LoggedInData;
import com.androidtesttask.data.model.LoggedInPicture;


public class MainViewModel extends ViewModel {

    private MainRepository mainRepos;
    MainViewModel(MainRepository mainRepository) {
        this.mainRepos = mainRepository;
    }

    private MutableLiveData<MainResult> mainResult = new MutableLiveData<>();
    private MutableLiveData<LoggedInPicture> loggedInPictureMutableLiveData = new MutableLiveData<>();

    LiveData<MainResult> getMainResult() {
        return mainResult;
    }

    LiveData<LoggedInPicture> getLoggedInPicture() {
        return loggedInPictureMutableLiveData;
    }

    public void getData(String code, int page) {
        Result<LoggedInData> result = mainRepos.getData(code, page);

        if (result instanceof Result.Success) {
            LoggedInData data = ((Result.Success<LoggedInData>) result).getData();
            mainResult.setValue(new MainResult(new MainDataView(data.getDataList())));
        } else {
            mainResult.setValue(new MainResult(R.string.data_failed));
        }
    }

    public void getPicture() {
        Result<LoggedInPicture> result = mainRepos.getPicture();
        LoggedInPicture data = null;
        if (result instanceof Result.Success) {
            data = ((Result.Success<LoggedInPicture>) result).getData();
        }

        loggedInPictureMutableLiveData.setValue(data);
    }

}
