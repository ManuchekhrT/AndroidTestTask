package com.androidtesttask.data;

import com.androidtesttask.data.model.LoggedIn;
import com.androidtesttask.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;
    private LoggedIn aUser = null;


    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedIn(LoggedIn aUser) {
        this.aUser = aUser;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }


    public Result<LoggedIn> login(String username, String password) {
        // handle login
        Result<LoggedIn> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedIn(((Result.Success<LoggedIn>) result).getData());
        }
        if (result instanceof Result.UnSuccess) {
            setLoggedIn(((Result.UnSuccess<LoggedIn>) result).getData());
        }
        return result;
    }
}