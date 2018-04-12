package com.aydashah.productscatalog.listener;

import retrofit2.Call;

/**
 * Created by AydaShah on 4/11/18.
 */

public interface AdapterListener<T> {
    void onStartLoadingList();

    void onEmptyResponse(T response);

    void onNoneEmptyResponse(T response);

    void onError(Call<T> call, Throwable throwable);

}
