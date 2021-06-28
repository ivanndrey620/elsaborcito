package com.androidapp.elsaborcito.inventory

import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentInventoryBinding
import com.androidapp.elsaborcito.inventory.adapter.InventoryListItemAdapter
import com.androidapp.elsaborcito.inventory.category_adapter.CategoryListItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class InventoryFragment : BaseFragment<FragmentInventoryBinding>() {

    private val adapter: InventoryListItemAdapter by lazy {
        InventoryListItemAdapter(viewModel.onItemListClickListener)
    }

    private val categoryAdapter: CategoryListItemAdapter by lazy {
        CategoryListItemAdapter(viewModel.onCategoryClickListener)
    }

    override val viewModel: InventoryViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_inventory

    override fun onDataBindingViewReady(binding: FragmentInventoryBinding) {
        binding.adapter = adapter
        binding.categoryAdapter = categoryAdapter

        observe(viewModel.event) { event ->
            when (event) {
                is LDInventoryEvent.NotifyAdapter -> {
                    categoryAdapter.refresh(event.category.id)
                }
                is LDInventoryEvent.OnEditCategoryImgClick -> navigator.navigateToAddCategory()
                is LDInventoryEvent.SetCategoryListAdapter -> categoryAdapter.setData(event.list)
                is LDInventoryEvent.OnCreateProductButtonClick -> navigator.navigateToAddProduct(null)
                is LDInventoryEvent.SetListAdapter -> adapter.setData(event.list)
                is LDInventoryEvent.NavigateToProduct -> navigator.navigateToProduct(event.item)
            }
        }
    }
}