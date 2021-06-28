package com.androidapp.elsaborcito.new_sale.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.databinding.ListItemNewSaleBinding

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class NewSaleListItemAdapter(private val listener: ItemListClickListener<Inventory>): BaseListAdapter<Inventory, NewSaleListItemViewModel, NewSaleListItemViewHolder, ListItemNewSaleBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_new_sale

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): NewSaleListItemViewHolder {
        return NewSaleListItemViewHolder(view)
    }

    override fun getViewModel(
        holder: NewSaleListItemViewHolder,
        item: Inventory,
        position: Int
    ): NewSaleListItemViewModel {
        return NewSaleListItemViewModel(item, position + 1 , listener)
    }
}