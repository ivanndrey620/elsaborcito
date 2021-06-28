package com.androidapp.elsaborcito.home

import com.androidapp.core.generic.Pref
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun onDataBindingViewReady(binding: FragmentHomeBinding) {
        observe(viewModel.event) { event ->
            when (event) {
                is LDHomeEvent.OnNewSaleParentClick -> navigator.navigateToNewSale()
                is LDHomeEvent.OnAddButtonClick -> navigator.navigateToAddProduct(null)
                is LDHomeEvent.OnInventoryParentClick -> navigator.navigateToInventory()
                is LDHomeEvent.OnSettingsParentClick -> {
                    Pref.deleteLogIn()
                    navigator.navigateToLogIn()
                }
            }
        }
    }
}