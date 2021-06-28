package com.androidapp.elsaborcito.sale_confirmation

import android.util.Log
import com.androidapp.core.livedata.observe
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentSaleConfirmationBinding
import com.androidapp.elsaborcito.extension.showOkAlert
import com.androidapp.elsaborcito.sale_confirmation.adapter.SaleConfirmationListItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleConfirmationFragment : BaseFragment<FragmentSaleConfirmationBinding>() {

    private val adapter: SaleConfirmationListItemAdapter by lazy {
        SaleConfirmationListItemAdapter(viewModel.onItemListClickListener)
    }


    override val viewModel: SaleConfirmationViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_sale_confirmation

    override fun onDataBindingViewReady(binding: FragmentSaleConfirmationBinding) {
        binding.adapter = adapter

        observe(viewModel.event) { event ->
            when (event) {
                is LDSaleConfirmationEvent.SetListAdapter -> adapter.setData(event.item)
                is LDSaleConfirmationEvent.OnDeleteItemClick -> {
                    showOkAlert("Aviso", "Desea quitar este producto?") {
                        adapter.remove(event.item)
                        viewModel.renewData(event.item)
                        viewModel.hasData.set(adapter.data.isNotEmpty())
                    }
                }
            }
        }
    }
}