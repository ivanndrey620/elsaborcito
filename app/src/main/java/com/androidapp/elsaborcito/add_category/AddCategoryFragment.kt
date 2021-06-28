package com.androidapp.elsaborcito.add_category

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import com.androidapp.core.extension.toast
import com.androidapp.core.generic.ResourceFetcher
import com.androidapp.core.livedata.observe
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.base.extension.hideKeyboard
import com.androidapp.elsaborcito.categories.CategoriesFragment
import com.androidapp.elsaborcito.databinding.FragmentAddCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    override val viewModel: AddCategoryViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_add_category

    override fun onDataBindingViewReady(binding: FragmentAddCategoryBinding) {

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(value: CharSequence, start: Int, before: Int, count: Int) {
                if (value.isNotEmpty()) {
                    binding.create.isEnabled = true
                    binding.create.isClickable = true
                    binding.create.background = ResourceFetcher.getDrawable(R.drawable.curve_button)
                    binding.create.setTextColor(ResourceFetcher.getColor(R.color.white))
                } else {
                    binding.create.isEnabled = false
                    binding.create.isClickable = false
                    binding.create.background =
                        ResourceFetcher.getDrawable(R.drawable.disabled_button)
                    binding.create.setTextColor(ResourceFetcher.getColor(R.color.search_no_content_grey))
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        observe(viewModel.event) { event ->
            when (event) {
                is LDAddCategoryEvent.OnCloseFragment -> removeFragment()
                is LDAddCategoryEvent.OnFailure -> {
                    event.message?.let {
                        toast(it)
                    }
                }
                is LDAddCategoryEvent.OnSuccess -> {
                    removeFragment()
                    binding.name.hideKeyboard(true)
                }
            }
        }
    }

    private fun removeFragment() {
        parentFragmentManager.beginTransaction().remove(this).commit()
    }

    companion object {
        const val TAG = "AddCategoryFragment"

        fun newInstance(): AddCategoryFragment = AddCategoryFragment()
    }
}