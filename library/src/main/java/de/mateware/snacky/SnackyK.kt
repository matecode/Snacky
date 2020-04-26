package de.mateware.snacky

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

class SnackyK {

    enum class Type(@ColorInt color: Int?, @DrawableRes iconResId: Int?, @ColorInt standardTextColor: Int?) {
        DEFAULT(null, null, null),
        SUCCESS(Color.parseColor("#388E3C"),
                R.drawable.ic_check_black_24dp,
                Color.WHITE),
        ERROR(Color.parseColor("#D50000"),
                R.drawable.ic_clear_black_24dp,
                Color.WHITE),
        INFO(Color.parseColor("#3F51B5"),
                R.drawable.ic_info_outline_black_24dp,
                Color.WHITE),
        WARNING(Color.parseColor("#FFA900"),
                R.drawable.ic_error_outline_black_24dp,
                Color.BLACK)
    }

    class Builder private constructor() {
        var view: View? = null
        var type: Type = Type.DEFAULT
        var duration: Int = Snackbar.LENGTH_SHORT
        var text: CharSequence = ""
        @StringRes var textResId: Int = 0
        var textColor: Int? = null
        var textColors: ColorStateList? = null
        var textSizeUnit: Int? = null
        val textSize: Float? = null
        var textTypefaceStyle: Int? = null
        var textTypeface: Typeface? = null
        var actionTextSizeUnit: Int? = null
        var actionTextSize: Int? = null
        var actionText: CharSequence = ""
        var actionTextResId: Int = 0
        var actionTextTypefaceStyle: Int? = null
        var actionTextTypeface: Typeface? = null
        var actionClickListener: View.OnClickListener? = null
        var actionTextColor: Int? = null
        var actionTextColors: ColorStateList? = null
        var maxLines: Int = Int.MAX_VALUE
        var centerText: Boolean = false
        var icon: Drawable? = null
        var iconResId: Int = 0
        var backgroundColor: Int? = null
    }
}