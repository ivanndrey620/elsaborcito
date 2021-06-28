package com.androidapp.elsaborcito.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidapp.core.activity.BaseActivity
import com.androidapp.core.livedata.observe
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.add_product.AddProductFragment
import com.androidapp.elsaborcito.base.UserDataProvider
import com.androidapp.elsaborcito.base.navigation.Navigator
import com.androidapp.elsaborcito.categories.CategoriesFragment
import com.androidapp.elsaborcito.databinding.ActivityMainBinding
import com.androidapp.elsaborcito.home.HomeFragment
import com.androidapp.elsaborcito.inventory.InventoryFragment
import com.androidapp.elsaborcito.new_sale.NewSaleFragment
import com.androidapp.elsaborcito.product.ProductFragment
import com.androidapp.elsaborcito.sale.SaleFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController: NavController by lazy { findNavController(R.id.main_nav_host) }
    private val userDataProvider: UserDataProvider by inject()
    private val navigator: Navigator by lazy { Navigator(navController, userDataProvider) }

    override val viewModel: MainViewModel by viewModel()

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun layoutRes(): Int = R.layout.activity_main

    override fun onDataBindingViewReady(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, binding.drawerLayout)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.title = ""
            binding.toolbar.visibility = View.GONE

            when ((destination as FragmentNavigator.Destination).className) {
                InventoryFragment::class.java.canonicalName -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.toolbar.title = "Inventario"
                }
                AddProductFragment::class.java.canonicalName -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.toolbar.title = "Agregar Producto"
                }
                SaleFragment::class.java.canonicalName -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.toolbar.title = "Nueva Venta"
                }

                CategoriesFragment::class.java.canonicalName -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.toolbar.title = "Editar Categorias"
                }
                ProductFragment::class.java.canonicalName -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.toolbar.title = "Resumen Producto"
                }
                else -> {
                    binding.toolbar.visibility = View.GONE
                }
            }
        }

        observe(viewModel.event) { event ->
            when (event) {
                is LDMainEvent.NavigateToLogin -> navigator.navigateToLogIn()
                is LDMainEvent.NavigateToHome -> navigator.navigateToHome()
            }
        }

    }
}