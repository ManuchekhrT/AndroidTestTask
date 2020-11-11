package com.androidtesttask.ui.main;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtesttask.R;
import com.androidtesttask.data.model.Data;
import com.androidtesttask.data.model.LoggedInPicture;
import com.androidtesttask.ui.map.MapsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {

    private MainViewModel mainViewModel;
    public final static String ARG_CODE = "ARG_CODE";
    private int currentPage = 0;
    private MainAdapter mainAdapter;
    private RecyclerView rvDataItems;
    private ProgressBar pbLoading;
    private ProgressBar pbLoadingMore;

    private RecyclerView.OnScrollListener scrollListener;
    private boolean isLoading = false;
    private LinearLayoutManager linearLayoutManager;
    private String code;

    private int getLastVisibleItemPosition() {
        return linearLayoutManager.findLastVisibleItemPosition();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory())
                .get(MainViewModel.class);

        initProgressBar();
        configureRecyclerView();

        code = getIntent().getStringExtra(ARG_CODE);

        setRecyclerViewScrollListener();
        fetchData();
        mainViewModel.getMainResult().observe(this, new Observer<MainResult>() {
            @Override
            public void onChanged(@Nullable MainResult mainResult) {
                if (mainResult == null)
                    return;

                pbLoading.setVisibility(View.GONE);
                pbLoadingMore.setVisibility(View.GONE);
                if (mainResult.getError() != null) {
                    showDataFailed(mainResult.getError());
                }
                if (mainResult.getSuccess() != null) {
                    updateUiWithData(mainResult.getSuccess());
                }
            }
        });

        //load picture
        mainViewModel.getPicture();
        mainViewModel.getLoggedInPicture().observe(this, new Observer<LoggedInPicture>() {
            @Override
            public void onChanged(@Nullable LoggedInPicture loggedInPicture) {
                if (loggedInPicture == null)
                    return;

                if (loggedInPicture.getPicture() != null)
                    mainAdapter.changeItem(loggedInPicture.getPicture());
            }
        });

    }

    private void initProgressBar() {
        pbLoading = findViewById(R.id.pb_loading);
        pbLoadingMore = findViewById(R.id.pb_loading_more);
    }

    private void fetchData() {
        currentPage += 1;
        if (currentPage == 1) pbLoading.setVisibility(View.VISIBLE);
        else pbLoadingMore.setVisibility(View.VISIBLE);

        mainViewModel.getData(code, currentPage);
    }

    private void setRecyclerViewScrollListener() {
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItemCount = rvDataItems.getLayoutManager().getItemCount();
                if (!isLoading) {
                    if (totalItemCount == getLastVisibleItemPosition() + 1) {
                        isLoading = true;
                        fetchData();
                        rvDataItems.removeOnScrollListener(scrollListener);
                    }
                }
            }
        };
        rvDataItems.addOnScrollListener(scrollListener);
    }

    private void configureRecyclerView() {
        rvDataItems = findViewById(R.id.rv_data);
        mainAdapter = new MainAdapter(this);
        rvDataItems.setAdapter(mainAdapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataItems.setLayoutManager(linearLayoutManager);
    }

    private void updateUiWithData(MainDataView dataView) {
        if (!dataView.getDataList().isEmpty()) {
            displayData(dataView.getDataList());
            setRecyclerViewScrollListener();
        } else isLoading = true;
    }

    private void displayData(List<Data> dataList) {
        mainAdapter.changeDataSet(dataList);
        isLoading = false;
    }

    private void showDataFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Data item) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(MapsActivity.ARG_DATA, item);
        startActivity(intent);
    }
}