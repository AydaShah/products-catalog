package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AydaShah on 4/12/18.
 */

public class RatingReviewsSummaryModel {

    @SerializedName("ratings_total")
    private int ratingsTotal;
    @SerializedName("reviews_total")
    private int reviewsTotal;
    private float average;

    public int getRatingsTotal() {
        return ratingsTotal;
    }

    public void setRatingsTotal(int ratingsTotal) {
        this.ratingsTotal = ratingsTotal;
    }

    public int getReviewsTotal() {
        return reviewsTotal;
    }

    public void setReviewsTotal(int reviewsTotal) {
        this.reviewsTotal = reviewsTotal;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
