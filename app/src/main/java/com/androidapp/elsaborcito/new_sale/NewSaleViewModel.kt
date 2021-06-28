package com.androidapp.elsaborcito.new_sale

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.api.persistence.model.TodaySale
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class NewSaleViewModel(private val appDataProvider: AppDataProvider) : ScreenViewModel(),
    LoadingState {

    override val isLoading: ObservableBoolean = ObservableBoolean()

    private val inventoryList: MutableList<Inventory> = mutableListOf()
    private val todaySaleList: MutableList<Inventory> = mutableListOf()

    private val _event = SingleLiveEvent<LDNewSaleEvent>()
    val event: LiveData<LDNewSaleEvent> = _event

    val onItemListClickListener = object : ItemListClickListener<Inventory> {
        override fun onItemListClickListener(item: Inventory, position: Int) {
            _event.postValue(LDNewSaleEvent.NewSale(item))
        }
    }


    override fun onBindView() {
        isLoading.set(true)

        appDataProvider.db.collection("products").addSnapshotListener { snapshot, error ->
            if (error != null) {
                isLoading.set(false)
                return@addSnapshotListener
            }

            snapshot?.let { querySnapshot ->
                querySnapshot.documents.forEach { document ->
                    val inventory = document.toObject(Inventory::class.java)
                    inventory?.let {
                        it.id = document.id
                        inventoryList.add(it)
                    }
                }
                isLoading.set(false)
                _event.postValue(LDNewSaleEvent.SetListAdapter(inventoryList))
            }
        }
    }

    fun search(text: String) {
        Log.d("AndyTag", "text $text")
    }

    fun addTodaySale(item: Inventory) {
        todaySaleList.add(item)
        appDataProvider.todaySales.onNext(todaySaleList)
    }
}