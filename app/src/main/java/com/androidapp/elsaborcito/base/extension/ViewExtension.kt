package com.androidapp.elsaborcito.base.extension

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.androidapp.elsaborcito.R

/**
 * Hide keyboard
 *
 * @param clearFocus: Clear focus from view
 */
fun EditText.hideKeyboard(clearFocus: Boolean = true) {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)

    if (clearFocus) {
        this.clearFocus()
    }
}

fun TextView.setSearchNoContentColor() {
    val grey: Int = ContextCompat.getColor(context, R.color.search_no_content_grey)
    val blue: Int = ContextCompat.getColor(context, R.color.search_no_content_blue)
    val text: String = context.getString(R.string.search_term_not_found)
    val spannable: Spannable = SpannableString(text)
    spannable.setSpan(ForegroundColorSpan(grey), 0, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannable.setSpan(ForegroundColorSpan(blue), 18, 29, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    spannable.setSpan(ForegroundColorSpan(grey), 29, text.count(), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    setText(spannable, TextView.BufferType.SPANNABLE)
}