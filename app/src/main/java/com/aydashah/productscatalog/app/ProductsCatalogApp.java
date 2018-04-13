package com.aydashah.productscatalog.app;

import android.app.Application;

import com.aydashah.productscatalog.utils.FontsOverride;

/**
 * Created by AydaShah on 4/10/18.
 */

public class ProductsCatalogApp extends Application{

    private static ProductsCatalogApp singleton;

    public static ProductsCatalogApp getInstance(){
        return singleton;
    }

    public ProductsCatalogApp(){
        super();
        singleton=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.initTypeFace(getAssets());
    }

}
