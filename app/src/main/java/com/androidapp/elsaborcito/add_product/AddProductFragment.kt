package com.androidapp.elsaborcito.add_product

import android.util.Log
import com.androidapp.core.extension.toast
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.base.extension.hide
import com.androidapp.elsaborcito.base.extension.hideKeyboard
import com.androidapp.elsaborcito.databinding.FragmentAddProductBinding
import com.androidapp.elsaborcito.extension.showOkAlert
import com.androidapp.elsaborcito.select_category.SelectCategoryFragment
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {
    override val viewModel: AddProductViewModel by viewModel {
        parametersOf(arguments?.getParcelable(ARG_ADD_PRODUCT))
    }

    override fun layoutRes(): Int = R.layout.fragment_add_product

    override fun onDataBindingViewReady(binding: FragmentAddProductBinding) {
        binding.name.requestFocus()
        observe(viewModel.event) { event ->
            when (event) {
                is LDAddProductEvent.ShowCategoryFragment -> {

                    when {
                        binding.name.isFocused -> binding.name.hideKeyboard()
                        binding.availableField.isFocused -> binding.availableField.hideKeyboard()
                        binding.priceField.isFocused -> binding.priceField.hideKeyboard()
                        binding.salePriceField.isFocused -> binding.salePriceField.hideKeyboard()
                    }

                    SelectCategoryFragment.show(
                        childFragmentManager,
                        R.id.container
                    ) { category, cancelled ->
                        childFragmentManager.hide(SelectCategoryFragment.TAG)
                        viewModel.showContent.set(true)
                        if (!cancelled) {
                            viewModel.category = category
                            viewModel.categoryProduct.set(category.name)
                        }
                    }
                }
                is LDAddProductEvent.OnBackgroundViewClicked -> binding.container.removeAllViews()
                is LDAddProductEvent.FieldsAreEmpty -> showOkAlert(
                    "Error",
                    "Debe llenar los campos para poder agregar"
                )
                is LDAddProductEvent.OnSuccess -> toast("Producto agregado correctamente")
                is LDAddProductEvent.OnFailure -> toast("Error => ${event.message}")
            }
        }
    }

    companion object {
        const val ARG_ADD_PRODUCT = "arg.add_product"
    }

}