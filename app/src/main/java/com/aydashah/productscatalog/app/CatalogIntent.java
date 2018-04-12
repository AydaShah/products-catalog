package com.aydashah.productscatalog.app;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by AydaShah on 4/12/18.
 */

public class CatalogIntent {

    public static Intent createProductsList() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("catalog://products_list"));
        return intent;
    }
}
