package com.aydashah.productscatalog.app.network.api;

import com.aydashah.productscatalog.app.model.response.ProductsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by AydaShah on 4/11/18.
 */

public interface ProductsApi {

    @GET("search/find/category/{CATEGORY}/page/{PAGE_NUMBER}/maxitems/{MAXIMUM_ITEMS_PER_PAGE}")
    Call<ProductsListResponse> getProductsListFromServer(@Path("CATEGORY") String category,
                                                         @Path("PAGE_NUMBER") String pageNumber,
                                                         @Path("MAXIMUM_ITEMS_PER_PAGE") String maxItemsPerPage);
}
