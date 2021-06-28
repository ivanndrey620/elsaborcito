package com.androidapp.elsaborcito.new_sale.adapter

import androidx.databinding.ObservableField
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.viewModel.ListItemViewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class NewSaleListItemViewModel(
    val item: Inventory,
    val position: Int,
    val listener: ItemListClickListener<Inventory>
) : ListItemViewModel() {

    val price = ObservableField("")
    val stock = ObservableField("")

    override fun onBindViewHolder() {
        super.onBindViewHolder()
        price.set("Q. ${item.price}")

        stock.set("Cantidad: ${item.stock}")
    }

}