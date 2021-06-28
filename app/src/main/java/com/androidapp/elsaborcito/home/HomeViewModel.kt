package com.androidapp.elsaborcito.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class HomeViewModel : ScreenViewModel() {

    private val _event = SingleLiveEvent<LDHomeEvent>()
    val event: LiveData<LDHomeEvent> = _event

    val gainToday = ObservableField("Q. 0.00")
    val todaySale = ObservableField("Q. 0.00")

    fun onInventoryParentClick() = _event.postValue(LDHomeEvent.OnInventoryParentClick)
    fun onSettingsParentClick() = _event.postValue(LDHomeEvent.OnSettingsParentClick)
    fun onAddButtonClick() = _event.postValue(LDHomeEvent.OnAddButtonClick)
    fun onNewSaleParentClick() = _event.postValue(LDHomeEvent.OnNewSaleParentClick)

}