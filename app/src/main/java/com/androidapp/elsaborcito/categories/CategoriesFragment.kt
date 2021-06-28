package com.androidapp.elsaborcito.categories

import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.add_category.AddCategoryFragment
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.categories.adapter.CategoriesListItemAdapter
import com.androidapp.elsaborcito.databinding.FragmentCategoriesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CategoriesFragment: BaseFragment<FragmentCategoriesBinding>() {

    private val adapter: CategoriesListItemAdapter = CategoriesListItemAdapter()

    override val viewModel: CategoriesViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_categories

    override fun onDataBindingViewReady(binding: FragmentCategoriesBinding) {
        binding.adapter = adapter

        observe(viewModel.event) { event ->
            when (event) {
                is LDCategoriesEvent.OnAddButtonClick -> showFragment()
                is LDCategoriesEvent.SetListAdapter -> adapter.setData(event.list)
                is LDCategoriesEvent.OnBackgroundViewClicked -> binding.container.removeAllViews()
            }
        }
    }

    private fun showFragment() {
        childFragmentManager.beginTransaction().apply {
            add(R.id.container, AddCategoryFragment.newInstance(), AddCategoryFragment.TAG)
            commit()
        }
    }

    companion object {

        fun newInstance(): CategoriesFragment = CategoriesFragment()
    }
}