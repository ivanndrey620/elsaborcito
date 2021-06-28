package com.androidapp.elsaborcito.product

import com.androidapp.api.persistence.model.Inventory

/**
 *  Created by Iván Bolaños on 27/06/2021
 */
sealed class LDProductEvent {

    class OnEditButtonClick(val item: Inventory) : LDProductEvent()

    class OnDeleteButtonClick(val inventory: Inventory): LDProductEvent()

    object OnDeleteSuccess: LDProductEvent()

    class OnFailure(val message: String?) : LDProductEvent()
}