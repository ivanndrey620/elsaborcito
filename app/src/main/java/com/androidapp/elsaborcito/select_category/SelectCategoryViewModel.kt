package com.androidapp.elsaborcito.select_category

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider
import com.androidapp.elsaborcito.base.CheckItem
import com.androidapp.elsaborcito.inventory.LDInventoryEvent

/**
 *  Created by Iván Bolaños on 26/06/2021
 */
class SelectCategoryViewModel(private val appDataProvider: AppDataProvider) : ScreenViewModel(),
    LoadingState {

    private val _event = SingleLiveEvent<LDSelectCategoryEvent>()
    val event: LiveData<LDSelectCategoryEvent> = _event

    override val isLoading: ObservableBoolean = ObservableBoolean()
    val showMessage = ObservableBoolean(true)

    private val categoriesList: MutableList<Category> = mutableListOf()

    val onItemListClickListener = object : ItemListClickListener<Category> {
        override fun onItemListClickListener(item: Category, position: Int) {
            _event.postValue(LDSelectCategoryEvent.OnCategorySelected(item))
        }
    }

    override fun onBindView() {
        super.onBindView()
        populateView()
    }


    private fun populateView() {
        if (appDataProvider.categories.value == null) {
            getCategories()
        } else {
            appDataProvider.categories.value?.let { list ->
                val refactorList = list.filter { it.id != "1" }
                _event.postValue(LDSelectCategoryEvent.SetListAdapter(refactorList))
            }
        }
    }

    private fun getCategories() {
        isLoading.set(true)
        categoriesList.clear()
        appDataProvider.db.collection("categories").addSnapshotListener { snapshot, error ->
            if (error != null) {
                isLoading.set(false)
                return@addSnapshotListener
            }
            snapshot?.let { querySnapshot ->
                querySnapshot.documents.forEach { document ->
                    val category = document.toObject(Category::class.java)
                    category?.let {
                        it.id = document.id
                        categoriesList.add(it)
                    }
                }
                isLoading.set(false)
                showMessage.set(categoriesList.isEmpty())
                appDataProvider.categories.onNext(categoriesList)
                _event.postValue(LDSelectCategoryEvent.SetListAdapter(categoriesList))
            }
        }
    }


    fun onAddButtonClick() = _event.postValue(LDSelectCategoryEvent.NavigateToAddCategory)
}