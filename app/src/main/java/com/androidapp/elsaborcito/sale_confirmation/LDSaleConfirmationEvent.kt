package com.androidapp.elsaborcito.sale_confirmation

import com.androidapp.api.persistence.model.Inventory

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDSaleConfirmationEvent {

    class SetListAdapter(val item: List<Inventory>): LDSaleConfirmationEvent()

    class OnDeleteItemClick(val item: Inventory, val position: Int): LDSaleConfirmationEvent()
}