package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.listener.AdapterListener;

import retrofit2.Call;

/**
 * Created by AydaShah on 4/10/18.
 */

public class BaseFragment<T> extends Fragment implements AdapterListener<T>, View.OnClickListener {

    protected TextView mEmptyView;
    protected TextView mErrorMessageTextView;
    protected LinearLayout mNetworkProblemView;
    protected RecyclerView mDefaultRecyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNetworkProblemView = view.findViewById(R.id.networkProblemView);
        mEmptyView = view.findViewById(R.id.emptyView);
        mErrorMessageTextView = view.findViewById(R.id.errorMessageTextView);
        mDefaultRecyclerView = view.findViewById(R.id.defaultRecyclerView);

        view.findViewById(R.id.retryButton).setOnClickListener(this);

    }

    @Override
    public void onStartLoadingList() {
        mNetworkProblemView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onEmptyResponse(T response) {
        mEmptyView.setVisibility(View.VISIBLE);
        mDefaultRecyclerView.setVisibility(View.GONE);
        mNetworkProblemView.setVisibility(View.GONE);
    }

    @Override
    public void onNoneEmptyResponse(T response) {
        mDefaultRecyclerView.setVisibility(View.VISIBLE);
        mNetworkProblemView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onError(Call<T> call, Throwable throwable) {
        if(!call.isCanceled()) {
            mErrorMessageTextView.setText(throwable.getMessage());
            mDefaultRecyclerView.setVisibility(View.GONE);
            mNetworkProblemView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retryButton:
                loadData();
                break;
        }
    }

    protected void loadData() {
    }

    protected void reloadData(){}
}
