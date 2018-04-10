package com.aydashah.productscatalog.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.aydashah.productscatalog.R;

/**
 * Created by AydaShah on 4/10/18.
 */

public class ProductListFragment extends BaseFragment {

    private GridView productsGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        productsGridView = (GridView) view.findViewById(R.id.productsGridView);
    }
}
