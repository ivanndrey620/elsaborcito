package com.androidapp.elsaborcito.sale_confirmation.adapter

import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.viewModel.ListItemViewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleConfirmationListItemViewModel(
    val item: Inventory,
    val position: Int,
    val listener: ItemListClickListener<Inventory>
) : ListItemViewModel()