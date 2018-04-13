package com.aydashah.productscatalog.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontsOverride {

    public static Typeface getFarsiFont() {
        return farsiFont;
    }

    public static void setFarsiFont(Typeface farsiFont) {
        FontsOverride.farsiFont = farsiFont;
    }

    static Typeface farsiFont;

    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);

        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void initTypeFace(AssetManager manager) { // TODO: remember to change font for dialogs in app theme
        farsiFont = Typeface.createFromAsset(manager, "fonts/IRANSans.ttf");

        try {
            final Field defaultFont = Typeface.class.getDeclaredField("SERIF");
            defaultFont.setAccessible(true);
            defaultFont.set(null, farsiFont);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
