package com.androidapp.elsaborcito.sale_confirmation

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider
import com.androidapp.elsaborcito.new_sale.LDNewSaleEvent
import timber.log.Timber

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleConfirmationViewModel(private val appDataProvider: AppDataProvider) : ScreenViewModel() {

    private val _event = SingleLiveEvent<LDSaleConfirmationEvent>()
    val event: LiveData<LDSaleConfirmationEvent> = _event

    val hasData = ObservableBoolean(false)

    override fun onBindView() {
        super.onBindView()

        appDataProvider.todaySales.subscribe({ todaySale ->
            hasData.set(todaySale.isNotEmpty())
            _event.postValue(LDSaleConfirmationEvent.SetListAdapter(todaySale))
        }, Timber::e).bindToLifecycle()
    }

    val onItemListClickListener = object : ItemListClickListener<Inventory> {
        override fun onItemListClickListener(item: Inventory, position: Int) {
            _event.postValue(LDSaleConfirmationEvent.OnDeleteItemClick(item, position))
        }
    }

    fun renewData(item: Inventory) {
        appDataProvider.todaySales.value?.remove(item)
    }

}