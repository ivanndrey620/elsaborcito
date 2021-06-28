package com.androidapp.elsaborcito.add_product

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDAddProductEvent {

    object OnSuccess : LDAddProductEvent()

    class OnFailure(val message: String?): LDAddProductEvent()

    object OnBackgroundViewClicked: LDAddProductEvent()

    object FieldsAreEmpty: LDAddProductEvent()
    object ShowCategoryFragment: LDAddProductEvent()
}