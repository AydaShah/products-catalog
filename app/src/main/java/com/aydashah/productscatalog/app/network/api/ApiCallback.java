package com.aydashah.productscatalog.app.network.api;

import com.aydashah.productscatalog.app.model.response.ErrorResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by AydaShah on 4/11/18.
 */

public interface ApiCallback<T> {

    void onResponse(Call<T> call, Response<T> response, ErrorResponse errorResponse);

    void onFailure(Call<T> call, Throwable t);

}
