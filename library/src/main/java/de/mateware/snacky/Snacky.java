package de.mateware.snacky;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by Mate on 18.02.2017.
 */

public class Snacky {

    private enum Type {
        DEFAULT(null, null, null), SUCCESS(Color.parseColor("#388E3C"), R.drawable.ic_check_black_24dp, Color.WHITE), ERROR(
                Color.parseColor("#D50000"), R.drawable.ic_clear_black_24dp, Color.WHITE), INFO(Color.parseColor("#3F51B5"),
                                                                                                R.drawable.ic_info_outline_black_24dp,
                                                                                                Color.WHITE), WARNING(
                Color.parseColor("#FFA900"), R.drawable.ic_error_outline_black_24dp, Color.BLACK);

        private Integer color;
        private Integer iconResId;
        private Integer standardTextColor;

        Type(@ColorInt Integer color, @DrawableRes Integer iconResId, @ColorInt Integer standardTextColor) {
            this.color = color;
            this.iconResId = iconResId;
            this.standardTextColor = standardTextColor;
        }

        public Integer getColor() {
            return color;
        }

        public Drawable getIcon(Context context) {
            if (iconResId == null) return null;
            Drawable drawable = SnackyUtils.tintDrawable(ContextCompat.getDrawable(context, iconResId), standardTextColor);
            //drawable.setAlpha(100);
            return drawable;
        }


        public Integer getStandardTextColor() {
            return standardTextColor;
        }
    }

    private final Builder builder;

    private Snacky(Builder builder) {
        this.builder = builder;
    }

    private Snackbar make() {

        Snackbar snackbar = Snackbar.make(builder.view, builder.text, builder.duration);

        if (builder.actionClickListener != null || builder.actionText != null) {
            if (builder.actionClickListener == null) builder.actionClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
            snackbar.setAction(builder.actionText, builder.actionClickListener);
            if (builder.actionTextColor == null) builder.actionTextColor = builder.type.getStandardTextColor();
            if (builder.actionTextColors != null) snackbar.setActionTextColor(builder.actionTextColors);
            else if (builder.actionTextColor != null) snackbar.setActionTextColor(builder.actionTextColor);

        }

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        if (builder.backgroundColor == null) builder.backgroundColor = builder.type.getColor();
        if (builder.backgroundColor != null) snackbarLayout.setBackgroundColor(builder.backgroundColor);

        TextView actionText = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_action);
        if (builder.actionTextSize != null) {
            if (builder.actionTextSizeUnit != null) actionText.setTextSize(builder.actionTextSizeUnit, builder.actionTextSize);
            else actionText.setTextSize(builder.actionTextSize);
        }
        if (builder.actionTextTypefaceStyle != null) {
            actionText.setTypeface(actionText.getTypeface(), builder.actionTextTypefaceStyle);
        }


        TextView text = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);

        if (builder.textSize != null) {
            if (builder.textSizeUnit != null) text.setTextSize(builder.textSizeUnit, builder.textSize);
            else text.setTextSize(builder.textSize);
        }
        if (builder.textTypefaceStyle != null) {
            text.setTypeface(text.getTypeface(), builder.textTypefaceStyle);
        }


        if (builder.textColor == null) builder.textColor = builder.type.getStandardTextColor();
        if (builder.textColors != null) text.setTextColor(builder.textColors);
        else if (builder.textColor != null) text.setTextColor(builder.textColor);
        text.setMaxLines(builder.maxLines);
        text.setGravity(builder.centerText ? Gravity.CENTER : Gravity.CENTER_VERTICAL);
        if (builder.centerText && Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
            text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        if (builder.icon == null) builder.icon = builder.type.getIcon(builder.view.getContext());
        if (builder.icon != null) {
            Drawable transparentHelperDrawable = null;
            if (builder.centerText && TextUtils.isEmpty(builder.actionText)) {
                transparentHelperDrawable = SnackyUtils.makeTransparentDrawable(builder.view.getContext(),
                                                                                builder.icon.getIntrinsicWidth(),
                                                                                builder.icon.getIntrinsicHeight());
            }
            text.setCompoundDrawablesWithIntrinsicBounds(builder.icon, null, transparentHelperDrawable, null);
            text.setCompoundDrawablePadding(text.getResources()
                                                .getDimensionPixelOffset(R.dimen.snacky_icon_padding));
        }


        return snackbar;
    }

    public static Builder builder() {
        return new Builder();
    }

    @RestrictTo(LIBRARY_GROUP)
    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @IntRange(from = 1)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static final int LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE;
    public static final int LENGTH_SHORT      = Snackbar.LENGTH_SHORT;
    public static final int LENGTH_LONG       = Snackbar.LENGTH_LONG;


    public static class Builder {

        private View                 view                    = null;
        private Type                 type                    = Type.DEFAULT;
        private int                  duration                = Snackbar.LENGTH_SHORT;
        private CharSequence         text                    = "";
        private int                  textResId               = 0;
        private Integer              textColor               = null;
        private ColorStateList       textColors              = null;
        private Integer              textSizeUnit            = null;
        private Float                textSize                = null;
        private Integer              textTypefaceStyle       = null;
        private Integer              actionTextSizeUnit      = null;
        private Float                actionTextSize          = null;
        private CharSequence         actionText              = "";
        private int                  actionTextResId         = 0;
        private Integer              actionTextTypefaceStyle = null;
        private View.OnClickListener actionClickListener     = null;
        private Integer              actionTextColor         = null;
        private ColorStateList       actionTextColors        = null;
        private int                  maxLines                = Integer.MAX_VALUE;
        private boolean              centerText              = false;
        private Drawable             icon                    = null;
        private int                  iconResId               = 0;
        private Integer              backgroundColor         = null;

        private Builder() {
        }

        public Builder setActivty(Activity activty) {
            return setView(activty.findViewById(android.R.id.content).getRootView());
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        public Builder setText(@StringRes int resId) {
            this.textResId = resId;
            return this;
        }

        public Builder setText(CharSequence text) {
            this.textResId = 0;
            this.text = text;
            return this;
        }

        public Builder setTextColor(@ColorInt int color) {
            this.textColor = color;
            return this;
        }

        public Builder setTextColor(ColorStateList colorStateList) {
            this.textColor = null;
            this.textColors = colorStateList;
            return this;
        }

        public Builder setTextSize(float textSize) {
            this.textSizeUnit = null;
            this.textSize = textSize;
            return this;
        }

        public Builder setTextSize(int unit, float textSize) {
            this.textSizeUnit = unit;
            this.textSize = textSize;
            return this;
        }

        public Builder setTextTypefaceStyle(int style) {
            this.textTypefaceStyle = style;
            return this;
        }

        public Builder centerText() {
            this.centerText = true;
            return this;
        }

        public Builder setActionTextColor(ColorStateList colorStateList) {
            this.actionTextColor = null;
            this.actionTextColors = colorStateList;
            return this;
        }

        public Builder setActionTextColor(@ColorInt int color) {
            this.actionTextColor = color;
            return this;
        }

        public Builder setActionText(@StringRes int resId) {
            this.actionTextResId = resId;
            return this;
        }

        public Builder setActionText(CharSequence text) {
            this.textResId = 0;
            this.actionText = text;
            return this;
        }

        public Builder setActionTextSize(float textSize) {
            this.actionTextSizeUnit = null;
            this.actionTextSize = textSize;
            return this;
        }

        public Builder setActionTextSize(int unit, float textSize) {
            this.actionTextSizeUnit = unit;
            this.actionTextSize = textSize;
            return this;
        }

        public Builder setActionTextTypefaceStyle(int style) {
            this.actionTextTypefaceStyle = style;
            return this;
        }

        public Builder setActionClickListener(View.OnClickListener listener) {
            this.actionClickListener = listener;
            return this;
        }

        public Builder setMaxLines(int maxLines) {
            this.maxLines = maxLines;
            return this;
        }

        public Builder setDuration(@Duration int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setIcon(@DrawableRes int resId) {
            this.iconResId = resId;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.icon = drawable;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Snackbar build() {
            return make();
        }

        public Snackbar success() {
            type = Type.SUCCESS;
            return make();
        }

        public Snackbar info() {
            type = Type.INFO;
            return make();
        }

        public Snackbar warning() {
            type = Type.WARNING;
            return make();
        }

        public Snackbar error() {
            type = Type.ERROR;
            return make();
        }

        private Snackbar make() {
            if (view == null) throw new IllegalStateException("Snacky Error: you must set Activtyt or view before make a snack");
            if (textResId != 0) text = view.getResources()
                                           .getText(textResId);
            if (actionTextResId != 0) actionText = view.getResources()
                                                       .getText(actionTextResId);
            if (iconResId != 0) icon = ContextCompat.getDrawable(view.getContext(), iconResId);
            return new Snacky(this).make();
        }
    }


}
