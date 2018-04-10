package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aydashah.productscatalog.R;

/**
 * Created by AydaShah on 4/10/18.
 */

public class ProductListFragment extends BaseFragment {

    private RecyclerView productsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        productsRecyclerView = (RecyclerView) view.findViewById(R.id.productsRecyclerView);

        productsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        productsRecyclerView.setLayoutManager(layoutManager);
    }
}
