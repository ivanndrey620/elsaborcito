package com.androidapp.elsaborcito.inventory

import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.elsaborcito.base.CheckItem

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDInventoryEvent {

    class SetListAdapter(val list: List<Inventory>): LDInventoryEvent()
    class SetCategoryListAdapter(val list: List<CheckItem>): LDInventoryEvent()
    object OnCreateProductButtonClick: LDInventoryEvent()
    object OnEditCategoryImgClick: LDInventoryEvent()
    class NotifyAdapter(val category: Category): LDInventoryEvent()
    class NavigateToProduct(val item: Inventory): LDInventoryEvent()
}