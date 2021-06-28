package com.androidapp.core.list

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.core.extension.safeLet
import com.androidapp.core.viewModel.ListItemViewModel

/**
 */
abstract class BaseViewHolder<VDB : ViewDataBinding, VM : ListItemViewModel>(val view: View) : RecyclerView.ViewHolder(view) {

    private var binding : VDB? = DataBindingUtil.bind(view)
    private var viewModel: VM? = null
    private var variableId: Int? = null

    fun setViewModel(variableId: Int, viewModel: VM) {
        this.viewModel = viewModel
        this.variableId = variableId
        binding?.setVariable(variableId, viewModel)
        viewModel.onBindViewHolder()
    }

    fun bind() {
        if (binding == null) {
            binding = DataBindingUtil.bind(view)

            safeLet(binding, viewModel, variableId) { b, v, vi ->
                b.setVariable(vi, v)
            }
        }
    }

    fun unbind() {
        viewModel?.onUnBindViewHolder()
        binding?.unbind()
        binding = null
    }

    fun executePendingBindings() {
        binding?.executePendingBindings()
    }
}