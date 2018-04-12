package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aydashah.productscatalog.R;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailFragment extends BaseFragment implements View.OnClickListener {

    private TextView mEmptyView;
    private TextView mErrorMessageTextView;
    private LinearLayout mNetworkProblemView;
    private String TAG = "ProductDetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //TODO cancel request
    }

    protected void loadData() {
    }

    private void initView(View view) {
        mNetworkProblemView = view.findViewById(R.id.networkProblemView);
        mEmptyView = view.findViewById(R.id.emptyView);
        mErrorMessageTextView = view.findViewById(R.id.errorMessageTextView);

        view.findViewById(R.id.retryButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retryButton:
                loadData();
                break;
        }
    }
}
