package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AydaShah on 4/12/18.
 */

class BrandEntityModel {

    @SerializedName("url_key")
    private String urlKey;
    private long id;
    private String name;

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
