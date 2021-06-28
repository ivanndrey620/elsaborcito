package com.androidapp.elsaborcito.new_sale

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import com.androidapp.core.extension.toast
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.base.extension.hideKeyboard
import com.androidapp.elsaborcito.databinding.FragmentNewSaleBinding
import com.androidapp.elsaborcito.extension.showAlert
import com.androidapp.elsaborcito.extension.showOkAlert
import com.androidapp.elsaborcito.new_sale.adapter.NewSaleListItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class NewSaleFragment: BaseFragment<FragmentNewSaleBinding>() {
    private var currentSearchDrawable: Int = R.drawable.ic_search

    private val adapter: NewSaleListItemAdapter by lazy {
        NewSaleListItemAdapter(viewModel.onItemListClickListener)
    }
    override val viewModel: NewSaleViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_new_sale

    override fun onDataBindingViewReady(binding: FragmentNewSaleBinding) {

        binding.adapter = adapter

        setSearchIcon(binding.search, binding.clearSearchButton)

        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText: String = binding.search.text.toString()
                viewModel.search(searchText)
                binding.search.hideKeyboard()
            }
            actionId == EditorInfo.IME_ACTION_SEARCH
        }

        binding.search.doOnTextChanged { _, _, _, _ -> setSearchIcon(binding.search, binding.clearSearchButton) }
        binding.clearSearchButton.setOnClickListener { binding.search.text.clear() }

        observe(viewModel.event) { event ->
            when (event) {
                is LDNewSaleEvent.SetListAdapter -> adapter.setData(event.list)
                is LDNewSaleEvent.NewSale -> showAlert("Aviso", "Desea agregar este producto ${event.item.name}") {
                    viewModel.addTodaySale(event.item)
                    toast("Producto agregado")
                }
            }
        }
    }

    private fun setSearchIcon(search: EditText, clearSearchButton: ImageView) {
        val searchText: String = search.text.toString()
        val newSearchDrawable: Int =
            if (searchText.isEmpty()) R.drawable.ic_search else R.drawable.ic_clear

        if (newSearchDrawable != currentSearchDrawable) {
            currentSearchDrawable = newSearchDrawable
            clearSearchButton.setImageResource(currentSearchDrawable)
        }
    }

    override fun onPause() {
        binding?.search?.hideKeyboard(true)
        super.onPause()
    }
}