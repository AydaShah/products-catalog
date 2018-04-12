package com.aydashah.productscatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AydaShah on 4/12/18.
 */

public class SpecificationsModel {

    @SerializedName("head_label")
    private String headLable;
    private ArrayList<SpecificationsBodyModel> body;

    public String getHeadLable() {
        return headLable;
    }

    public void setHeadLable(String headLable) {
        this.headLable = headLable;
    }

    public ArrayList<SpecificationsBodyModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<SpecificationsBodyModel> body) {
        this.body = body;
    }
}
