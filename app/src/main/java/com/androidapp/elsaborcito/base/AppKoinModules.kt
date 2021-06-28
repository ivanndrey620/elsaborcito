package com.androidapp.elsaborcito.base

import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.generic.ResourceFetcher
import com.androidapp.elsaborcito.add_category.AddCategoryViewModel
import com.androidapp.elsaborcito.add_product.AddProductViewModel
import com.androidapp.elsaborcito.categories.CategoriesFragment
import com.androidapp.elsaborcito.categories.CategoriesViewModel
import com.androidapp.elsaborcito.home.HomeViewModel
import com.androidapp.elsaborcito.inventory.InventoryViewModel
import com.androidapp.elsaborcito.login.LoginViewModel
import com.androidapp.elsaborcito.main.MainViewModel
import com.androidapp.elsaborcito.new_sale.NewSaleViewModel
import com.androidapp.elsaborcito.product.ProductViewModel
import com.androidapp.elsaborcito.sale.SaleViewModel
import com.androidapp.elsaborcito.sale_confirmation.SaleConfirmationViewModel
import com.androidapp.elsaborcito.select_category.SelectCategoryViewModel
import com.androidapp.elsaborcito.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
object AppKoinModules {

    val appModule: Module = module {
        single { UserDataProvider() }
        single { AppDataProvider() }

        viewModel { SplashViewModel() }
        viewModel { MainViewModel(get()) }

    }

    private val logInModule: Module = module {
        viewModel { LoginViewModel() }
    }

    private val homeModule: Module = module {
        viewModel { HomeViewModel() }
        viewModel { InventoryViewModel(get()) }
        viewModel { (product: Inventory?) -> AddProductViewModel(product, get()) }
        viewModel { SaleViewModel() }
        viewModel { NewSaleViewModel(get()) }
        viewModel { SaleConfirmationViewModel(get()) }
        viewModel { CategoriesViewModel(get()) }
        viewModel { AddCategoryViewModel(get()) }
        viewModel { SelectCategoryViewModel(get()) }
        viewModel { (product: Inventory) -> ProductViewModel(product, get()) }
    }

    fun loadLogInModules() = loadKoinModules(logInModule)

    fun loadHomeModules() = loadKoinModules(homeModule)

    fun clearDynamicModules() {
        unloadKoinModules(logInModule)
        unloadKoinModules(homeModule)
    }


}