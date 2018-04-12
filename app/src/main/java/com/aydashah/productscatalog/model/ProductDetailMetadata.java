package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailMetadata {

    @SerializedName("price_converted")
    private double priceConverted;
    @SerializedName("category_entity")
    private CategoryEntityModel categoryEntity;
    @SerializedName("brand_entity")
    private BrandEntityModel brandEntity;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("rating_reviews_summary")
    private RatingReviewsSummaryModel ratingReviewsSummary;
    @SerializedName("image_list")
    private ArrayList<ImageModel> imageList;
    private String sku;
    private String name;
    private long price;
    private String categories;
    private String vertical;
    private boolean bundle;
    private ArrayList<SpecificationsModel> specifications;

    public double getPriceConverted() {
        return priceConverted;
    }

    public void setPriceConverted(double priceConverted) {
        this.priceConverted = priceConverted;
    }

    public CategoryEntityModel getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntityModel categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public BrandEntityModel getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntityModel brandEntity) {
        this.brandEntity = brandEntity;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public RatingReviewsSummaryModel getRatingReviewsSummary() {
        return ratingReviewsSummary;
    }

    public void setRatingReviewsSummary(RatingReviewsSummaryModel ratingReviewsSummary) {
        this.ratingReviewsSummary = ratingReviewsSummary;
    }

    public ArrayList<ImageModel> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImageModel> imageList) {
        this.imageList = imageList;
    }

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public ArrayList<SpecificationsModel> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(ArrayList<SpecificationsModel> specifications) {
        this.specifications = specifications;
    }
}
