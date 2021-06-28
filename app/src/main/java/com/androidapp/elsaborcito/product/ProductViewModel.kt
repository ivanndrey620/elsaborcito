package com.androidapp.elsaborcito.product

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.AppDataProvider

/**
 *  Created by Iván Bolaños on 27/06/2021
 */
class ProductViewModel(
    private val product: Inventory,
    private val appDataProvider: AppDataProvider
) : ScreenViewModel() {

    private val _event = SingleLiveEvent<LDProductEvent>()
    val event: LiveData<LDProductEvent> = _event

    val stock = ObservableField("")
    val productName = ObservableField("")
    val price = ObservableField("")
    val salePrice = ObservableField("")
    val category = ObservableField("")

    override fun onBindView() {
        super.onBindView()
        stock.set(product.stock.toString())
        productName.set(product.name)
        price.set("Q. ${product.price}")
        salePrice.set("Q. ${product.salePrice}")

        appDataProvider.categories.value?.let { list ->
            list.filter { it.id == product.category }.forEach { categoryData ->
                category.set(categoryData.name)
            }
        }
    }

    fun onEditButtonClick() = _event.postValue(LDProductEvent.OnEditButtonClick(product))

    fun onDeleteButtonClick() = _event.postValue(LDProductEvent.OnDeleteButtonClick(product))

    fun deleteProduct(productId: String) {
        appDataProvider.db.collection("products")
            .document(productId)
            .delete()
            .addOnSuccessListener {
                _event.postValue(LDProductEvent.OnDeleteSuccess)
            }
            .addOnFailureListener { e ->
                _event.postValue(LDProductEvent.OnFailure(e.message))
            }
    }
}