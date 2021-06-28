package com.androidapp.elsaborcito.select_category

import androidx.fragment.app.FragmentManager
import com.androidapp.api.persistence.model.Category
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.base.extension.hide
import com.androidapp.elsaborcito.databinding.FragmentSelectCategoryBinding
import com.androidapp.elsaborcito.select_category.adapter.SelectCategoryListItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 26/06/2021
 */
class SelectCategoryFragment : BaseFragment<FragmentSelectCategoryBinding>() {

    private lateinit var callback: (category: Category, cancelled: Boolean) -> Unit

    private val adapter: SelectCategoryListItemAdapter by lazy {
        SelectCategoryListItemAdapter(viewModel.onItemListClickListener)
    }

    override val viewModel: SelectCategoryViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_select_category

    override fun onDataBindingViewReady(binding: FragmentSelectCategoryBinding) {

        binding.adapter = adapter

        observe(viewModel.event) { event ->
            when (event) {
                is LDSelectCategoryEvent.SetListAdapter -> adapter.setData(event.list)
                is LDSelectCategoryEvent.OnCategorySelected -> callback.invoke(event.category, false)
                is LDSelectCategoryEvent.NavigateToAddCategory -> navigator.navigateToAddCategory()
            }
        }
    }

    companion object {
        const val TAG = "SelectCategoryFragment"

        fun show(
            fragmentManager: FragmentManager,
            container: Int,
            callback: (category: Category, cancelled: Boolean) -> Unit
        ) {
            val fragment =   SelectCategoryFragment()
            fragment.callback = callback
            fragmentManager.hide(TAG)

            // show dialog fragment
            fragmentManager.beginTransaction().add(container, fragment, TAG).commit()
        }
    }
}