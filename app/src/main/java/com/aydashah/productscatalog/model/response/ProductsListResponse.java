package com.aydashah.productscatalog.model.response;

import com.aydashah.productscatalog.model.ProductsListMetadata;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ProductsListResponse extends BaseResponse {

    private ProductsListMetadata metadata;

    public ProductsListMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ProductsListMetadata metadata) {
        this.metadata = metadata;
    }
}
