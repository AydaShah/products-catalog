package com.aydashah.productscatalog.app.network;

import com.aydashah.productscatalog.app.model.response.ErrorResponse;
import com.aydashah.productscatalog.app.network.api.ApiCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ApiClient {
    private static ApiClient sApiClient;
    private Retrofit.Builder adapterBuilder;
    private Retrofit mRetrofit;

    public static ApiClient getInstance() {
        if (sApiClient == null) {
            sApiClient = new ApiClient();

        }
        return sApiClient;

    }

    private ApiClient() {
        createDefaultAdapter();
    }

    public <T> T getEndpointApi(Class<T> service) {
        return mRetrofit.create(service);
    }

    private void createDefaultAdapter() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkManager.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> void request(Call<T> call, final ApiCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (callback == null) {
                    return;
                }

                Gson gson = new GsonBuilder().create();
                ErrorResponse errorResponse=gson.fromJson(response.body().toString(), ErrorResponse.class);

                callback.onResponse(call, response, errorResponse);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

}
