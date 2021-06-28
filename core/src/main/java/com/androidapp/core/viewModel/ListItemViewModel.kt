package com.androidapp.core.viewModel

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
abstract class ListItemViewModel: BaseViewModel() {

    /**
     * Called when the RecyclerView.ViewHolder is attached
     */
    /**
     * Called when the RecyclerView.ViewHolder is attached
     */
    open fun onBindViewHolder() { }

    /**
     * Called when the RecyclerView.ViewHolder is detached
     */
    /**
     * Called when the RecyclerView.ViewHolder is detached
     */
    open fun onUnBindViewHolder() {
        pendingJobs.cancel()
        disposable.clear()
    }
}