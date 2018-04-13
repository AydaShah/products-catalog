package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.adapter.ProductDetailListAdapter;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailFragment extends BaseFragment {

    private ProductDetailListAdapter mAdapter;
    private String mSku;
    private String TAG = "ProductDetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
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
        initView(view);
        mAdapter = new ProductDetailListAdapter(this, mSku);
        mDefaultRecyclerView.setAdapter(mAdapter);
        mAdapter.load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter.cancelLoadData();
    }

    @Override
    protected void loadData() {
        mAdapter.load();
    }

    private void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDefaultRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void reloadData() {
        mAdapter.reload();
    }

}
