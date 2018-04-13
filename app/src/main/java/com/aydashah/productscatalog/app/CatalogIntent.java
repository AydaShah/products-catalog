package com.aydashah.productscatalog.app;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by AydaShah on 4/12/18.
 */

public class CatalogIntent {

    public static Intent createProductsListIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("catalog://products_list"));
        return intent;
    }

    public static Intent createProductsDetailIntent(String sku) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("catalog://products_detail?sku=" + sku));
        return intent;
    }
}
