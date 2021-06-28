package com.androidapp.elsaborcito.new_sale

import com.androidapp.api.persistence.model.Inventory

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDNewSaleEvent {

    class SetListAdapter(val list: List<Inventory>): LDNewSaleEvent()
    class NewSale(val item: Inventory): LDNewSaleEvent()
}