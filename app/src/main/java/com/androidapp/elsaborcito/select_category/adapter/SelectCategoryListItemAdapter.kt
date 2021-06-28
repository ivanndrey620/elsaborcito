package com.androidapp.elsaborcito.select_category.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Category
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.databinding.ListItemSelectCategoryBinding

/**
 *  Created by Iván Bolaños on 26/06/2021
 */
class SelectCategoryListItemAdapter(private val listener: ItemListClickListener<Category>) :
    BaseListAdapter<Category, SelectCategoryListItemViewModel, SelectCategoryListItemViewHolder, ListItemSelectCategoryBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_select_category

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): SelectCategoryListItemViewHolder {
        return SelectCategoryListItemViewHolder(view)
    }

    override fun getViewModel(
        holder: SelectCategoryListItemViewHolder,
        item: Category,
        position: Int
    ): SelectCategoryListItemViewModel {
        return SelectCategoryListItemViewModel(item, position, listener)
    }

}