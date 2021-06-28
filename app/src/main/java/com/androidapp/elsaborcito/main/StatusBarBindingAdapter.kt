package com.androidapp.elsaborcito.main

import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
@BindingAdapter(value = ["constraintLayoutForStatusBar"])
fun setToolbarStatusBarMargin(toolbar: Toolbar, constraintLayout: ConstraintLayout) {

    ViewCompat.setOnApplyWindowInsetsListener(constraintLayout) { _, insets ->
        val lp = toolbar.layoutParams as ConstraintLayout.LayoutParams
        lp.topMargin = insets.systemWindowInsetTop
        insets
    }
}