package com.androidapp.elsaborcito.add_category

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Category
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class AddCategoryViewModel(private val appDataProvider: AppDataProvider): ScreenViewModel(), LoadingState {

    override val isLoading: ObservableBoolean = ObservableBoolean()

    private val _event = SingleLiveEvent<LDAddCategoryEvent>()
    val event: LiveData<LDAddCategoryEvent> = _event

    val categoryName = ObservableField<String>()

    fun onCloseFragment() = _event.postValue(LDAddCategoryEvent.OnCloseFragment)

    fun onCreateButtonClick() {
        isLoading.set(true)
        val name = categoryName.get() ?: ""
        val category = Category(name = name)
        appDataProvider.db.collection("categories")
            .document().set(category)
            .addOnSuccessListener {
                isLoading.set(false)
                _event.postValue(LDAddCategoryEvent.OnSuccess)
            }
            .addOnFailureListener {
                isLoading.set(false)
                _event.postValue(LDAddCategoryEvent.OnFailure(it.message))
            }
    }
}