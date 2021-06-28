package com.androidapp.elsaborcito.inventory.category_adapter

import android.view.View
import androidx.annotation.UiThread
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.CheckItem
import com.androidapp.elsaborcito.databinding.ListItemCategoryBinding

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CategoryListItemAdapter(private val listener: ItemListClickListener<CheckItem>): BaseListAdapter<CheckItem, CategoryListItemViewModel, CategoryListItemViewHolder, ListItemCategoryBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_category

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): CategoryListItemViewHolder {
        return CategoryListItemViewHolder(view)
    }

    override fun getViewModel(
        holder: CategoryListItemViewHolder,
        item: CheckItem,
        position: Int
    ): CategoryListItemViewModel {
        return CategoryListItemViewModel(item, position, listener)
    }

    @UiThread
    fun refresh(id: String) {
        data.filter { it.data.id != id }.forEach {
            it.isChecked.set(false)
        }
        notifyDataSetChanged()
    }


}