package com.aydashah.productscatalog.network.api;

import com.aydashah.productscatalog.model.response.ProductDetailResponse;
import com.aydashah.productscatalog.model.response.ProductsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by AydaShah on 4/11/18.
 */

public interface ProductsApi {

    @GET("search/find/category/{CATEGORY}/page/{PAGE_NUMBER}/maxitems/{MAXIMUM_ITEMS_PER_PAGE}")
    Call<ProductsListResponse> getProductsListFromServer(@Path("CATEGORY") String category,
                                                         @Path("PAGE_NUMBER") int pageNumber,
                                                         @Path("MAXIMUM_ITEMS_PER_PAGE") int maxItemsPerPage);

    @GET("catalog/detail/sku/{SKU_NUMBER}")
    Call<ProductDetailResponse> getProductDetailFromServer(@Path("SKU_NUMBER") String skuNumber);
}
