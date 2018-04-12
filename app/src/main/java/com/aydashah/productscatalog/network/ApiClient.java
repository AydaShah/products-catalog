package com.aydashah.productscatalog.network;

import com.aydashah.productscatalog.model.response.BaseResponse;
import com.aydashah.productscatalog.network.api.ApiCallback;
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

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new BaseResponse.MessagesDeserializer())
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkManager.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static <T> void request(Call<T> call, final ApiCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (callback == null) {
                    return;
                }

                callback.onResponse(call, response, null);
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
