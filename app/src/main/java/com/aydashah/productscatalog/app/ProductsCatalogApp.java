package com.aydashah.productscatalog.app;

import android.app.Application;

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

}
