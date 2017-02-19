package de.mateware.snacky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Mate on 19.02.2017.
 */

public class SnackyUtils {

    static Drawable tintDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        drawable = DrawableCompat.wrap(drawable);
        if (drawable != null) {
            drawable = drawable.mutate();
            DrawableCompat.setTint(drawable, color);
        }
        return drawable;
    }

    static Drawable makeTransparentDrawable(Context context, int width, int heigth) {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(width, heigth, conf);
        return new BitmapDrawable(context.getResources(),bmp);
    }
}
