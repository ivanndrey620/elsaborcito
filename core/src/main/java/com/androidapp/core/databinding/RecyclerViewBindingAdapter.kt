package com.androidapp.core.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.core.list.BaseListAdapter
import com.androidapp.core.list.ItemListClickListener

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
@BindingAdapter(value = ["adapter"], requireAll = false)
fun <T> bindAdapter(recyclerView: RecyclerView, adapter: BaseListAdapter<T, *, *, *>) {
    recyclerView.adapter = adapter
}

@BindingAdapter(value = ["adapter", "itemClickListener"], requireAll = true)
fun <T> bindAdapter(recyclerView: RecyclerView, adapter: BaseListAdapter<T, *, *, *>, listener: ItemListClickListener<T>) {
    recyclerView.adapter = adapter
    adapter.itemClickListener = listener
}