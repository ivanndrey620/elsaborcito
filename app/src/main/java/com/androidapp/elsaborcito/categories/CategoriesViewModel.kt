package com.androidapp.elsaborcito.categories

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CategoriesViewModel(private val appDataProvider: AppDataProvider) : ScreenViewModel(),
    LoadingState {

    override val isLoading: ObservableBoolean = ObservableBoolean()
    val showContent = ObservableBoolean(true)


    val showMessage = ObservableBoolean(true)

    private val _event = SingleLiveEvent<LDCategoriesEvent>()
    val event: LiveData<LDCategoriesEvent> = _event

    override fun onBindView() {
        super.onBindView()
        appDataProvider.categories.value?.let { list ->
            showMessage.set(list.isEmpty())
            _event.postValue(LDCategoriesEvent.SetListAdapter(list))
        }
    }

    fun onAddButtonClick() {
        showContent.set(false)
        _event.postValue(LDCategoriesEvent.OnAddButtonClick)
    }

    fun onBackgroundViewClick() {
        showContent.set(true)
        _event.postValue(LDCategoriesEvent.OnBackgroundViewClicked)
    }

}