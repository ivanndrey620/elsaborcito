package com.androidapp.elsaborcito.base.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.AppKoinModules
import com.androidapp.elsaborcito.base.UserDataProvider
import timber.log.Timber
import java.lang.IllegalStateException

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class Navigator(
    private val navigationController: NavController,
    private val userDataProvider: UserDataProvider
) : INavigator {
    override fun navigateToLogIn() {
        AppKoinModules.clearDynamicModules()
        AppKoinModules.loadLogInModules()

        try {
            navigationController.navigate(R.id.action_login)
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }

    }

    override fun navigateToHome() {

        AppKoinModules.clearDynamicModules()
        AppKoinModules.loadHomeModules()

        try {
            navigationController.navigate(R.id.action_home)
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun navigateToInventory() {
        try {
            navigationController.navigate(R.id.action_inventory)
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun navigateToAddProduct(inventory: Inventory?) {

        val bundle = Bundle()
        bundle.putParcelable(INavigator.ARG_ADD_PRODUCT, inventory)
        val navOptions = NavOptions.Builder()
        navOptions.setLaunchSingleTop(true)

        try {
            navigationController.navigate(R.id.action_add_product, bundle, navOptions.build())
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun navigateToNewSale() {
        try {
            navigationController.navigate(R.id.action_sale)
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun navigateToAddCategory() {
        try {
            navigationController.navigate(R.id.action_add_category)
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun navigateToProduct(product: Inventory) {

        val bundle = Bundle()
        bundle.putParcelable(INavigator.ARG_PRODUCT, product)
        val navOptions = NavOptions.Builder()
        navOptions.setLaunchSingleTop(true)

        try {
            navigationController.navigate(R.id.action_product, bundle, navOptions.build())
        } catch (exception: IllegalStateException) {
            Timber.e(exception)
        }
    }

    override fun back() {
        navigationController.popBackStack()
    }
}