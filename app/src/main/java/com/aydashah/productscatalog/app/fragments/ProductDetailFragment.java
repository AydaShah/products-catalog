package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.adapter.ProductDetailListAdapter;
import com.aydashah.productscatalog.listener.AdapterListener;

import retrofit2.Call;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailFragment extends BaseFragment implements View.OnClickListener, AdapterListener {

    private TextView mEmptyView;
    private TextView mErrorMessageTextView;
    private LinearLayout mNetworkProblemView;
    private RecyclerView mProductDetailRecyclerView;

    private ProductDetailListAdapter mAdapter;
    private String mSku;
    private String TAG = "ProductDetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSku = getArguments().getString("SKU");
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ProductDetailListAdapter(this, mSku);
        mProductDetailRecyclerView.setAdapter(mAdapter);
        mAdapter.load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter.cancelLoadData();
    }

    protected void loadData() {
        mAdapter.load();
    }

    private void initView(View view) {
//        mNetworkProblemView = view.findViewById(R.id.networkProblemView);
//        mEmptyView = view.findViewById(R.id.emptyView);
//        mErrorMessageTextView = view.findViewById(R.id.errorMessageTextView);
        mProductDetailRecyclerView = view.findViewById(R.id.productDetailRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mProductDetailRecyclerView.setLayoutManager(linearLayoutManager);

//        view.findViewById(R.id.retryButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retryButton:
                loadData();
                break;
        }
    }


    protected void reloadData() {
        mAdapter.reload();
    }

    @Override
    public void onStartLoadingList() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        mNetworkProblemView.setVisibility(View.GONE);
//        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onEmptyResponse(Object response) {
//        mEmptyView.setVisibility(View.VISIBLE);
//        mProductsRecyclerView.setVisibility(View.GONE);
//        mNetworkProblemView.setVisibility(View.GONE);
    }

    @Override
    public void onNoneEmptyResponse(Object response) {
//        mSwipeRefreshLayout.setRefreshing(false);
//        mProductsRecyclerView.setVisibility(View.VISIBLE);
//        mNetworkProblemView.setVisibility(View.GONE);
//        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onError(Call call, Throwable throwable) {
//        if (call.isCanceled()) {
//            Log.d(TAG, "onError: request was cancelled");
//        } else {
//            Log.d(TAG, "onError: " + throwable.getMessage());
//            mErrorMessageTextView.setText(throwable.getMessage());
//            mSwipeRefreshLayout.setRefreshing(false);
//            mProductsRecyclerView.setVisibility(View.GONE);
//            mNetworkProblemView.setVisibility(View.VISIBLE);
//            mEmptyView.setVisibility(View.GONE);
//        }
    }
}
