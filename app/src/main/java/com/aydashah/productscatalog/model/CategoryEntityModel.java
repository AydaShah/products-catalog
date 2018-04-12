package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AydaShah on 4/12/18.
 */

public class CategoryEntityModel {

    @SerializedName("url_key")
    private String urlKey;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }
}
