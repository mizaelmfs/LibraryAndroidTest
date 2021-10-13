package com.hello.library_test

import android.content.res.Resources
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import java.util.*

fun EditText.textCustom() : String = this.text.toString().trim()

fun TextInputLayout.clearError() {
    this.isErrorEnabled = false
}

fun View.gone() : View {
    if (visibility == View.VISIBLE) {
        this.visibility = View.GONE
    }
    return this
}

fun View.hide() : View {
    if (visibility == View.VISIBLE) {
        this.visibility = View.INVISIBLE
    }
    return this
}

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        this.visibility = View.VISIBLE
    }
    return this
}

fun ImageView.setImageTint(@ColorRes resource: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.imageTintList = ContextCompat.getColorStateList(context, resource)
    } else {
        this.setColorFilter(ContextCompat.getColor(context, resource), android.graphics.PorterDuff.Mode.SRC_IN)
    }
}

fun ImageView.setColorList(@ColorRes resource: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.imageTintList = ContextCompat.getColorStateList(context, resource)
    } else {
        this.setColorFilter(ContextCompat.getColor(context, resource), android.graphics.PorterDuff.Mode.MULTIPLY)
    }
}

fun View.dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

fun View.pxToDp(px: Int) = (px / Resources.getSystem().displayMetrics.density).toInt()

fun String.capitalizeFirstLetter() = this.split(" ").joinToString(" ") { it.capitalize(Locale.getDefault()) }
    .trimEnd()
