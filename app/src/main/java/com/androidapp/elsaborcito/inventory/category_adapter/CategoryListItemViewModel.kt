package com.androidapp.elsaborcito.inventory.category_adapter

import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.androidapp.api.persistence.model.Category
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.viewModel.ListItemViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.CheckItem

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CategoryListItemViewModel(val category: CheckItem, val position: Int, val listener: ItemListClickListener<CheckItem>) : ListItemViewModel() {

    @DrawableRes
    var curveBackground: Int = R.drawable.curve_background

    @DrawableRes
    var categoryBackground: Int = R.drawable.curve_button_category

    @ColorRes
    var black: Int = R.color.black

    @ColorRes
    var white: Int = R.color.white

}