package com.aydashah.productscatalog.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.app.fragments.ProductDetailFragment;
import com.aydashah.productscatalog.app.fragments.ProductListFragment;

/**
 * Created by AydaShah on 4/10/18.
 */

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(new ProductListFragment());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
        Uri intentData = getIntent().getData();
        if (intentData != null) {
            String section = intentData.getHost();
            if (section != null) {
                Fragment fragment;
                switch (section) {
                    case "products_list":
                        fragment = new ProductListFragment();
                        openFragment(fragment);
                        break;
                    case "products_detail":
                        Bundle productDetailBundle = new Bundle();
                        productDetailBundle.putString("SKU", intentData.getQueryParameter("sku"));
                        fragment = new ProductDetailFragment();
                        fragment.setArguments(productDetailBundle);
                        openFragment(fragment);
                        break;
                }
            }
        }
    }

    public void openFragment(Fragment fragment) throws IllegalStateException {
        FragmentManager childFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentHolder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentHolder);

        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
