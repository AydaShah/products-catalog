package com.aydashah.productscatalog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.app.CatalogIntent;
import com.aydashah.productscatalog.app.ProductsCatalogApp;
import com.aydashah.productscatalog.app.activities.MainActivity;
import com.aydashah.productscatalog.listener.AdapterListener;
import com.aydashah.productscatalog.model.ErrorMessages;
import com.aydashah.productscatalog.model.ErrorModel;
import com.aydashah.productscatalog.model.ProductModel;
import com.aydashah.productscatalog.model.response.ErrorResponse;
import com.aydashah.productscatalog.model.response.ProductsListResponse;
import com.aydashah.productscatalog.network.ApiClient;
import com.aydashah.productscatalog.network.api.ApiCallback;
import com.aydashah.productscatalog.network.api.ProductsApi;
import com.aydashah.productscatalog.utils.PersianNumberConverter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by AydaShah on 4/11/18.
 */

public class ProductsListAdapter extends RecyclerView.Adapter implements ApiCallback<ProductsListResponse> {

    private int mMaxItemPerPage = 35;
    private int mPagination;
    private String mCategory;
    private AdapterListener mAdapterListener;
    private boolean mIsLoading = false;
    private boolean mWholeListLoaded;
    private ArrayList<ProductModel> mData = new ArrayList<>();
    private Call<ProductsListResponse> call;


    public ProductsListAdapter(AdapterListener adapterListener, String category) {
        mPagination = 0;
        mAdapterListener = adapterListener;
        mCategory = category;
    }

    public void load() {
        Log.d("testAyda", "load: "+mPagination+"  "+mData.size());
        mIsLoading = true;
        ProductsApi api = ApiClient.getInstance().getEndpointApi(ProductsApi.class);
        call = api.getProductsListFromServer(mCategory, mPagination, mMaxItemPerPage);
        ApiClient.request(call, this);
        mAdapterListener.onStartLoadingList();
    }

    public void cancelLoadData() {
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    public void reload() {
        mData.clear();
        mIsLoading = true;
        mPagination = 0;
        notifyDataSetChanged();
        load();
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isWholeListLoaded() {
        return mWholeListLoaded;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductModel item = mData.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        final Context context = viewHolder.itemHolderRelativeLayout.getContext();

        if (item.getBrand() != null) {
            viewHolder.productBrandTextView.setText(item.getBrand());
        }
        if (item.getName() != null) {
            viewHolder.productNameTextView.setText(item.getName());
        }
        viewHolder.productPriceTextView.setText(PersianNumberConverter.convertToPersianFromString(
                PersianNumberConverter.separateBy3(item.getPrice()))
                + " " + context.getString(R.string.rial));
        try {
            Picasso.with(context)
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_product)
                    .fit().centerInside()
                    .into(viewHolder.productImageView, new ImageLoadedCallback(viewHolder.productImageProgressBar) {
                        @Override
                        public void onSuccess() {
                            this.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            this.progressBar.setVisibility(View.GONE);
                            viewHolder.productImageView.setImageResource(R.drawable.ic_product);
                        }
                    });
        } catch (Exception e) {
            viewHolder.productImageView.setImageResource(R.drawable.ic_product);
        }

        viewHolder.itemHolderRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startActivity(CatalogIntent.createProductsDetailIntent(item.getSku()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onResponse(Call<ProductsListResponse> call, Response<ProductsListResponse> response, ErrorResponse errorResponse) {
        mIsLoading = false;
        int currentItemCount = getItemCount();
        if (response.isSuccessful()) {
            if (response.body().isSuccess()) {
                ArrayList<ProductModel> items = response.body().getMetadata().getResults();
                mData.addAll(items);
                mPagination++;
                Log.d("testAyda", "onResponse: "+mData.size()+"  "+items.size()+"  "+response.body().getMetadata().getTotalProducts());
                if (mData.size() >= response.body().getMetadata().getTotalProducts()) {
                    mWholeListLoaded = true;
                }
                if (mData.isEmpty()) {
                    mAdapterListener.onEmptyResponse(response);
                } else {
                    notifyItemRangeInserted(currentItemCount, items.size());
                    mAdapterListener.onNoneEmptyResponse(response);
                }
            } else {
                String errorBody = "";
                ArrayList<ErrorModel> errorList = ((ErrorMessages) (response.body().getMessages())).getError();
                for (int i = 0; i < errorList.size(); i++) {
                    errorBody += errorList.get(i).getMessage() + "\n";
                }

                mAdapterListener.onError(call, new Exception(errorBody));
            }

        } else {
            mAdapterListener.onError(call, new Exception(
                    ProductsCatalogApp.getInstance().getApplicationContext().getString(
                            R.string.unsuccessful_operation)));
        }
    }

    @Override
    public void onFailure(Call<ProductsListResponse> call, Throwable t) {
        mAdapterListener.onError(call, t);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView productImageView;
        final ProgressBar productImageProgressBar;
        final TextView productBrandTextView;
        final TextView productNameTextView;
        final TextView productPriceTextView;
        final RelativeLayout itemHolderRelativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            productBrandTextView = itemView.findViewById(R.id.productBrandTextView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productImageProgressBar = itemView.findViewById(R.id.productImageProgressBar);
            itemHolderRelativeLayout = itemView.findViewById(R.id.itemHolderRelativeLayout);
        }

    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        public ImageLoadedCallback(ProgressBar progBar) {
            progressBar = progBar;
        }

        @Override
        public void onSuccess() {
        }

        @Override
        public void onError() {

        }
    }
}
