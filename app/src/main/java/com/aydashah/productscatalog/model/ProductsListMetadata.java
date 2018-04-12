package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ProductsListMetadata {

    private String sort;
    @SerializedName("total_products")
    private int totalProducts;
    private String title;
    private String categories;
    private String vertical;
    private ArrayList<ProductModel> results;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public ArrayList<ProductModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ProductModel> results) {
        this.results = results;
    }
}
