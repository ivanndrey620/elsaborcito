package com.androidapp.core.databinding

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
interface OkKeyboardClickListener {

    fun onOkKeyboardClick()

}

@BindingAdapter("okKeyboardClickListener")
fun bindOkKeyboardClickListener(editText: EditText, listener: OkKeyboardClickListener) {
    editText.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener.onOkKeyboardClick()
        }
        return@setOnEditorActionListener false
    }
}