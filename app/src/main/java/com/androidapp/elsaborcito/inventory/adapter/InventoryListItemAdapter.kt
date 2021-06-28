package com.androidapp.elsaborcito.inventory.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.databinding.ListItemInventoryBinding

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class InventoryListItemAdapter(private val listener: ItemListClickListener<Inventory>) :
    BaseListAdapter<Inventory, InventoryListItemViewModel, InventoryListItemViewHolder, ListItemInventoryBinding>() {
    override fun layoutRes(viewType: Int): Int = R.layout.list_item_inventory

    override fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        view: View,
        viewType: Int
    ): InventoryListItemViewHolder {
        return InventoryListItemViewHolder(view)
    }

    override fun getViewModel(
        holder: InventoryListItemViewHolder,
        item: Inventory,
        position: Int
    ): InventoryListItemViewModel {
        return InventoryListItemViewModel(item, position + 1, listener)
    }

    fun getByCategory(id: String) : List<Inventory> {
        return data.filter { it.category == id }
    }
}