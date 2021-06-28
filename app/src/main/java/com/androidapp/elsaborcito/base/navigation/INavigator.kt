package com.androidapp.elsaborcito.base.navigation

import com.androidapp.api.persistence.model.Inventory

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
interface INavigator {

    fun navigateToLogIn()

    fun navigateToHome()

    fun navigateToInventory()

    fun navigateToAddProduct(inventory: Inventory?)

    fun navigateToNewSale()

    fun navigateToAddCategory()

    fun navigateToProduct(product: Inventory)

    fun back()

    companion object {
        const val ARG_PRODUCT = "arg.product"
        const val ARG_ADD_PRODUCT = "arg.add_product"
    }
}