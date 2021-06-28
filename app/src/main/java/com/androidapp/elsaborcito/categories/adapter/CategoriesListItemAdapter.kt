package com.androidapp.elsaborcito.categories.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Category
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.databinding.ListItemCategoriesBinding

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CategoriesListItemAdapter :
    BaseListAdapter<Category, CategoriesListItemViewModel, CategoriesListItemViewHolder, ListItemCategoriesBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_categories

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): CategoriesListItemViewHolder {
        return CategoriesListItemViewHolder(view)
    }

    override fun getViewModel(
        holder: CategoriesListItemViewHolder,
        item: Category,
        position: Int
    ): CategoriesListItemViewModel {
        return CategoriesListItemViewModel(item)
    }
}