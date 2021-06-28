package com.androidapp.elsaborcito.product

import com.androidapp.core.extension.toast
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentProductBinding
import com.androidapp.elsaborcito.extension.showAlert
import com.androidapp.elsaborcito.extension.showOkAlert
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 *  Created by Iván Bolaños on 27/06/2021
 */
class ProductFragment : BaseFragment<FragmentProductBinding>() {
    override val viewModel: ProductViewModel by viewModel {
        parametersOf(arguments?.getParcelable(ARG_PRODUCT))
    }

    override fun layoutRes(): Int = R.layout.fragment_product

    override fun onDataBindingViewReady(binding: FragmentProductBinding) {

        observe(viewModel.event) { event ->
            when (event) {
                is LDProductEvent.OnDeleteSuccess -> {
                    toast("Eliminado correctamente")
                    navigator.back()
                }
                is LDProductEvent.OnDeleteButtonClick -> showAlert(
                    "Aviso",
                    "Esta seguro que desea eliminar este producto => ${event.inventory.name}?"
                ) {
                    viewModel.deleteProduct(event.inventory.id)
                }
                is LDProductEvent.OnEditButtonClick -> navigator.navigateToAddProduct(event.item)

                is LDProductEvent.OnFailure -> {
                    event.message?.let {
                        showOkAlert("Error", it)
                    }
                }
            }
        }
    }

    companion object {
        const val ARG_PRODUCT = "arg.product"

    }
}