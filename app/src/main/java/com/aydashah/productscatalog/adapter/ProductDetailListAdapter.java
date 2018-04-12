package com.aydashah.productscatalog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aydashah.productscatalog.R;
import com.aydashah.productscatalog.app.ProductsCatalogApp;
import com.aydashah.productscatalog.listener.AdapterListener;
import com.aydashah.productscatalog.model.ErrorMessages;
import com.aydashah.productscatalog.model.ErrorModel;
import com.aydashah.productscatalog.model.ProductDetailCustomModel;
import com.aydashah.productscatalog.model.ProductDetailMetadata;
import com.aydashah.productscatalog.model.SpecificationsBodyModel;
import com.aydashah.productscatalog.model.response.ErrorResponse;
import com.aydashah.productscatalog.model.response.ProductDetailResponse;
import com.aydashah.productscatalog.network.ApiClient;
import com.aydashah.productscatalog.network.api.ApiCallback;
import com.aydashah.productscatalog.network.api.ProductsApi;
import com.aydashah.productscatalog.utils.PersianNumberConverter;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by AydaShah on 4/12/18.
 */

public class ProductDetailListAdapter extends RecyclerView.Adapter implements ApiCallback<ProductDetailResponse> {

    private final int PRODUCT_DATA = 1;
    private final int PRODUCT_SPECIFICATION = 2;
    private String mSku;
    private AdapterListener mAdapterListener;
    private boolean mIsLoading = false;
    private ArrayList<ProductDetailCustomModel> mData = new ArrayList<>();
    private Call<ProductDetailResponse> call;


    public ProductDetailListAdapter(AdapterListener adapterListener, String sku) {
        mAdapterListener = adapterListener;
        mSku = sku;
    }

    public void load() {
        mIsLoading = true;
        ProductsApi api = ApiClient.getInstance().getEndpointApi(ProductsApi.class);
        call = api.getProductDetailFromServer(mSku);
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
        View v = null;
        switch (viewType) {
            case PRODUCT_DATA:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_detail_type_1, parent, false);
                break;
            case PRODUCT_SPECIFICATION:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_detail_type_2, parent, false);
                break;

        }
        return new ViewHolder(v);
    }

    public void reload() {
        mData.clear();
        notifyDataSetChanged();
        load();
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProductDetailCustomModel item = mData.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        final Context context = viewHolder.itemHolderLinearLayout.getContext();

        switch (item.getItemType()) {

            case PRODUCT_DATA:
                viewHolder.productBrandTextView.setText(item.getBrandEntity().getName());
                viewHolder.productNameTextView.setText(item.getName());
                viewHolder.productPriceTextView.setText(PersianNumberConverter.convertToPersianFromString(
                        PersianNumberConverter.separateBy3(item.getPrice()))
                        + " " + context.getString(R.string.rial));

                viewHolder.productImagesCarouselView.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        Picasso.with(context)
                                .load(item.getImageList().get(position).getUrl())
                                .placeholder(R.drawable.ic_product)
                                .fit().centerInside()
                                .into(imageView);
                    }
                });
                viewHolder.productImagesCarouselView.setPageCount(item.getImageList().size());

                break;

            case PRODUCT_SPECIFICATION:
                viewHolder.keyTextView.setText(item.getDetail().getKey());
                viewHolder.valueTextView.setText(item.getDetail().getValue());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response, ErrorResponse errorResponse) {
        mIsLoading = false;
        if (response.isSuccessful()) {
            if (response.body().isSuccess()) {
                ProductDetailMetadata productDetailMetadata = response.body().getMetadata();
                mData.clear();
                mData.addAll(fillList(productDetailMetadata));
                this.notifyDataSetChanged();

                if (mData.isEmpty()) {
                    mAdapterListener.onEmptyResponse(response);
                } else {
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
    public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
        mAdapterListener.onError(call, t);
    }

    private ArrayList<ProductDetailCustomModel> fillList(ProductDetailMetadata productDetailMetadata) {
        ArrayList<ProductDetailCustomModel> list = new ArrayList<>();
        ProductDetailCustomModel item = new ProductDetailCustomModel(productDetailMetadata.getBrandEntity(),
                productDetailMetadata.getRatingReviewsSummary(), productDetailMetadata.getImageList()
                , productDetailMetadata.getSku(), productDetailMetadata.getName(), productDetailMetadata.getPrice(),
                PRODUCT_DATA);
        list.add(item);

        for (int i = 0; i < productDetailMetadata.getSpecifications().get(0).getBody().size(); i++) {
            SpecificationsBodyModel detail = productDetailMetadata.getSpecifications().get(0).getBody().get(i);
            item = new ProductDetailCustomModel(detail, PRODUCT_SPECIFICATION);
            list.add(item);
        }

        return list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView productBrandTextView;
        final TextView productNameTextView;
        final TextView productPriceTextView;
        final CarouselView productImagesCarouselView;
        final RatingBar productRateBar;

        final TextView keyTextView;
        final TextView valueTextView;

        final LinearLayout itemHolderLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            productBrandTextView = itemView.findViewById(R.id.productBrandTextView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productImagesCarouselView = itemView.findViewById(R.id.productImagesCarouselView);
            productRateBar = itemView.findViewById(R.id.productRateBar);

            keyTextView = itemView.findViewById(R.id.keyTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);

            itemHolderLinearLayout = itemView.findViewById(R.id.itemHolderLinearLayout);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

}
