package com.aydashah.productscatalog.model.response;

import com.aydashah.productscatalog.model.ProductDetailMetadata;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailResponse extends BaseResponse{

    private ProductDetailMetadata metadata;

    public ProductDetailMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ProductDetailMetadata metadata) {
        this.metadata = metadata;
    }
}
