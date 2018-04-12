package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.adapter.ProductsListAdapter;
import com.aydashah.productscatalog.listener.AdapterListener;

import retrofit2.Call;

/**
 * Created by AydaShah on 4/10/18.
 */

public class ProductListFragment extends BaseFragment implements AdapterListener, View.OnClickListener {

    private RecyclerView mProductsRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mEmptyView;
    private LinearLayout mNetworkProblemView;
    private ProductsListAdapter mAdapter;
    private final String PRODUCT_CATEGORY = "men_shirts";
    private String TAG = "ProductListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
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
        if (mAdapter != null) {
            mAdapter.cancelLoadData();
        }
    }

    protected void loadData() {
        mAdapter = new ProductsListAdapter(this, PRODUCT_CATEGORY);
        mProductsRecyclerView.setAdapter(mAdapter);
        mAdapter.load();
        mProductsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mProductsRecyclerView.getLayoutManager().getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) mProductsRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (!mAdapter.isLoading() && !mAdapter.isWholeListLoaded() && totalItemCount <= (lastVisibleItem + 4)) {
                    mAdapter.load();
                }

            }
        });
    }

    protected void reloadData() {
        mAdapter.reload();
    }

    @Override
    public void onStartLoadingList() {
        mSwipeRefreshLayout.setRefreshing(true);
        mProductsRecyclerView.setVisibility(View.GONE);
        mNetworkProblemView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onEmptyResponse(Object response) {
        mEmptyView.setVisibility(View.VISIBLE);
        mProductsRecyclerView.setVisibility(View.GONE);
        mNetworkProblemView.setVisibility(View.GONE);
    }

    @Override
    public void onNoneEmptyResponse(Object response) {
        mSwipeRefreshLayout.setRefreshing(false);
        mProductsRecyclerView.setVisibility(View.VISIBLE);
        mNetworkProblemView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onError(Call call, Throwable throwable) {
        if (call.isCanceled()) {
            Log.d(TAG, "onError: request was cancelled");
        } else {
            Log.d(TAG, "onError: " + throwable.getMessage());
            mSwipeRefreshLayout.setRefreshing(false);
            mProductsRecyclerView.setVisibility(View.GONE);
            mNetworkProblemView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        mProductsRecyclerView = view.findViewById(R.id.productsRecyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mNetworkProblemView = view.findViewById(R.id.networkProblemView);
        mEmptyView = view.findViewById(R.id.emptyView);

        mProductsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mProductsRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

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
