package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.adapter.ProductsListAdapter;
import com.aydashah.productscatalog.listener.AdapterListener;

import retrofit2.Call;

/**
 * Created by AydaShah on 4/10/18.
 */

public class ProductListFragment extends BaseFragment implements AdapterListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProductsListAdapter mAdapter;
    private final String PRODUCT_CATEGORY = "men_shirts";
    private String TAG = "ProductListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAdapter != null) {
            mAdapter.cancelLoadData();
        }
    }

    @Override
    protected void loadData() {
        mAdapter = new ProductsListAdapter(this, PRODUCT_CATEGORY);
        mDefaultRecyclerView.setAdapter(mAdapter);
        mAdapter.load();
        mDefaultRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mDefaultRecyclerView.getLayoutManager().getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) mDefaultRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (!mAdapter.isLoading() && !mAdapter.isWholeListLoaded() && totalItemCount <= (lastVisibleItem + 4)) {
                    mAdapter.load();
                }

            }
        });
    }

    @Override
    protected void reloadData() {
        mAdapter.reload();
    }

    @Override
    public void onStartLoadingList() {
        super.onStartLoadingList();
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onEmptyResponse(Object response) {
        super.onEmptyResponse(response);
    }

    @Override
    public void onNoneEmptyResponse(Object response) {
        super.onNoneEmptyResponse(response);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Call call, Throwable throwable) {
        super.onError(call,throwable);
        if(!call.isCanceled()) {
            mSwipeRefreshLayout.setRefreshing(false);}
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        mDefaultRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mDefaultRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

    }
}
