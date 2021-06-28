package com.androidapp.elsaborcito.inventory

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider
import com.androidapp.elsaborcito.base.CheckItem

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class InventoryViewModel(private val appDataProvider: AppDataProvider) : ScreenViewModel(),
    LoadingState {

    private val inventoryList: MutableList<Inventory> = mutableListOf()
    private val categoriesList: MutableList<Category> = mutableListOf()

    private val _event = SingleLiveEvent<LDInventoryEvent>()
    val event: LiveData<LDInventoryEvent> = _event

    override val isLoading: ObservableBoolean = ObservableBoolean()

    val showMessage = ObservableBoolean(true)

    val totalProducts = ObservableField("0")
    val totalCost = ObservableField("Q.0.00")

    val onItemListClickListener = object : ItemListClickListener<Inventory> {
        override fun onItemListClickListener(item: Inventory, position: Int) {
            _event.postValue(LDInventoryEvent.NavigateToProduct(item))
        }
    }

    val onCategoryClickListener = object : ItemListClickListener<CheckItem> {
        override fun onItemListClickListener(item: CheckItem, position: Int) {
            _event.postValue(LDInventoryEvent.NotifyAdapter(item.data))
            if (!item.isChecked.get()) {
                item.toggle()
            }

            getProductsById(item.data.id)
        }
    }

    override fun onBindView() {
        super.onBindView()
        isLoading.set(true)
        getCategories()
    }

    private fun getCategories() {
        categoriesList.clear()
        val categoryDefault = Category("1", "Ver todos")

        categoriesList.add(0, categoryDefault)
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

                val categoryListCheckItem = categoriesList.map {
                    if (it.id == "1") {
                        CheckItem(it, true)
                    } else {
                        CheckItem(it, false)
                    }
                }
                appDataProvider.categories.onNext(categoriesList)
                _event.postValue(LDInventoryEvent.SetCategoryListAdapter(categoryListCheckItem))
                getProducts()
            }
        }
    }

    private fun getProducts() {
        inventoryList.clear()
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
                var total = 0f
                inventoryList.forEach { inventory ->
                    total += inventory.salePrice
                }
                totalCost.set("Q. $total")
                totalProducts.set("${inventoryList.size}")
                showMessage.set(inventoryList.isEmpty())
                _event.postValue(LDInventoryEvent.SetListAdapter(inventoryList))
            }
        }
    }

    private fun getProductsById(id: String) {
        if (id == "1") {
            getProducts()
        } else {
            inventoryList.clear()
            appDataProvider.db.collection("products")
                .whereEqualTo("category", id)
                .addSnapshotListener { snapshot, error ->
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
                        var total = 0f
                        inventoryList.forEach { inventory ->
                            total += inventory.salePrice
                        }
                        totalCost.set("Q. $total")
                        totalProducts.set("${inventoryList.size}")
                        showMessage.set(inventoryList.isEmpty())
                        _event.postValue(LDInventoryEvent.SetListAdapter(inventoryList))
                    }
                }
        }
    }

    fun onCreateProductButtonClick() = _event.postValue(LDInventoryEvent.OnCreateProductButtonClick)

    fun onEditCategoryImgClick() = _event.postValue(LDInventoryEvent.OnEditCategoryImgClick)

}