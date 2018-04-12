package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ProductModel {

    @SerializedName("category_entity")
    private CategoryEntityModel categoryEntity;
    @SerializedName("rating_reviews_summary")
    private RatingReviewsSummaryModel ratingReviewsSummary;
    private String sku;
    private String name;
    private String brand;
    private long price;
    private double price_converted;
    private String categories;
    private String image;
    private String target;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public double getPrice_converted() {
        return price_converted;
    }

    public void setPrice_converted(double price_converted) {
        this.price_converted = price_converted;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public CategoryEntityModel getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntityModel categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public RatingReviewsSummaryModel getRatingReviewsSummary() {
        return ratingReviewsSummary;
    }

    public void setRatingReviewsSummary(RatingReviewsSummaryModel ratingReviewsSummary) {
        this.ratingReviewsSummary = ratingReviewsSummary;
    }
}
