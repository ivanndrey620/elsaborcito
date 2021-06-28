package com.androidapp.elsaborcito.inventory.adapter

import androidx.databinding.ObservableField
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.viewModel.ListItemViewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class InventoryListItemViewModel(val item: Inventory, val position: Int, val listener: ItemListClickListener<Inventory>) : ListItemViewModel() {

    val stock = ObservableField("")
    val salePrice = ObservableField("")

    override fun onBindViewHolder() {
        super.onBindViewHolder()

        stock.set("${item.stock} Disponibles")

        salePrice.set("Q. ${item.salePrice}")
    }
}