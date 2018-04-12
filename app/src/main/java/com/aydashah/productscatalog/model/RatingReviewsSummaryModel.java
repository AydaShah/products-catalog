package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AydaShah on 4/12/18.
 */

public class RatingReviewsSummaryModel {

    @SerializedName("ratings_total")
    private String ratingsTotal;
    @SerializedName("reviews_total")
    private String reviewsTotal;
    private String average;

    public String getRatingsTotal() {
        return ratingsTotal;
    }

    public void setRatingsTotal(String ratingsTotal) {
        this.ratingsTotal = ratingsTotal;
    }

    public String getReviewsTotal() {
        return reviewsTotal;
    }

    public void setReviewsTotal(String reviewsTotal) {
        this.reviewsTotal = reviewsTotal;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
