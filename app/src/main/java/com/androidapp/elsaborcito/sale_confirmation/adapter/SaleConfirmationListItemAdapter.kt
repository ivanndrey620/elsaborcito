package com.androidapp.elsaborcito.sale_confirmation.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.databinding.ListItemSaleConfirmationBinding

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleConfirmationListItemAdapter(private val listener: ItemListClickListener<Inventory>): BaseListAdapter<Inventory, SaleConfirmationListItemViewModel, SaleConfirmationListViewHolder, ListItemSaleConfirmationBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_sale_confirmation

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): SaleConfirmationListViewHolder {
        return SaleConfirmationListViewHolder(view)
    }

    override fun getViewModel(
        holder: SaleConfirmationListViewHolder,
        item: Inventory,
        position: Int
    ): SaleConfirmationListItemViewModel {
        return SaleConfirmationListItemViewModel(item, position, listener)
    }
}