package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailCustomModel {

    @SerializedName("brand_entity")
    private BrandEntityModel brandEntity;
    @SerializedName("rating_reviews_summary")
    private RatingReviewsSummaryModel ratingReviewsSummary;
    @SerializedName("image_list")
    private ArrayList<ImageModel> imageList;
    private String sku;
    private String name;
    private long price;
    private SpecificationsBodyModel detail;
    private int itemType;

    public ProductDetailCustomModel(BrandEntityModel brandEntity, RatingReviewsSummaryModel ratingReviewsSummary, ArrayList<ImageModel> imageList, String sku, String name, long price, int itemType) {
        this.brandEntity = brandEntity;
        this.ratingReviewsSummary = ratingReviewsSummary;
        this.imageList = imageList;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.itemType = itemType;
    }

    public ProductDetailCustomModel(SpecificationsBodyModel detail, int itemType) {
        this.detail = detail;
        this.itemType = itemType;
    }

    public BrandEntityModel getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntityModel brandEntity) {
        this.brandEntity = brandEntity;
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

    public SpecificationsBodyModel getDetail() {
        return detail;
    }

    public void setDetail(SpecificationsBodyModel detail) {
        this.detail = detail;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
