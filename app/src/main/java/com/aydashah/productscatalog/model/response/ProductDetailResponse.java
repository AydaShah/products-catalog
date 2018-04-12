package com.aydashah.productscatalog.model.response;

import com.aydashah.productscatalog.model.ProductDetailMetadata;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailResponse extends BaseResponse{

    private ProductDetailMetadata productDetailMetadata;

    public ProductDetailMetadata getProductDetailMetadata() {
        return productDetailMetadata;
    }

    public void setProductDetailMetadata(ProductDetailMetadata productDetailMetadata) {
        this.productDetailMetadata = productDetailMetadata;
    }
}
