package com.androidapp.elsaborcito.add_product

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.core.databinding.OkKeyboardClickListener
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.generic.ResourceFetcher
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.AppDataProvider

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class AddProductViewModel(
    private val inventory: Inventory?,
    private val appDataProvider: AppDataProvider
) : ScreenViewModel(),
    LoadingState {

    lateinit var category: Category

    private val _event = SingleLiveEvent<LDAddProductEvent>()
    val event: LiveData<LDAddProductEvent> = _event

    override val isLoading: ObservableBoolean = ObservableBoolean()
    val showContent = ObservableBoolean(true)

    val productName = ObservableField<String>()
    val available = ObservableField<String>()
    val price = ObservableField<String>()
    val salePrice = ObservableField<String>()
    val categoryProduct = ObservableField<String>()
    val buttonText = ObservableField<String>(ResourceFetcher.getString(R.string.add))

    val okKeyboardClickListener: OkKeyboardClickListener = object : OkKeyboardClickListener {
        override fun onOkKeyboardClick() = onAddButtonClick()
    }

    override fun onBindView() {
        super.onBindView()
        inventory?.let {
            buttonText.set(ResourceFetcher.getString(R.string.edit))
            productName.set(it.name)
            available.set("${it.stock}")
            price.set("${it.price}")
            salePrice.set("${it.salePrice}")

            appDataProvider.categories.value?.let { list ->
                list.filter { it.id == inventory.category }.forEach { categoryData ->
                    category = categoryData
                    categoryProduct.set(categoryData.name)
                }
            }
        }
    }

    fun onAddButtonClick() {
        val productName = productName.get() ?: ""
        val available = available.get() ?: ""
        val price = price.get() ?: ""
        val salePrice = salePrice.get() ?: ""
        val categoryProduct = categoryProduct.get() ?: ""

        if (inventory != null) {
            isLoading.set(true)

            val updateInventory = Inventory(
                "",
                available.toInt(),
                productName,
                price.toFloat(),
                salePrice.toFloat(),
                category.id
            )

            appDataProvider.db.collection("products")
                .document(inventory.id)
                .set(updateInventory)
                .addOnSuccessListener {
                    clearFields()
                    isLoading.set(false)
                    _event.postValue(LDAddProductEvent.OnSuccess)
                }
                .addOnFailureListener { e ->
                    isLoading.set(false)
                    e.message?.let {
                        _event.postValue(LDAddProductEvent.OnFailure(it))
                    }
                }
        } else {

            if (productName.isNotEmpty() && available.isNotEmpty() && price.isNotEmpty() && salePrice.isNotEmpty() && categoryProduct.isNotEmpty()) {
                isLoading.set(true)

                val inventory = Inventory(
                    "",
                    available.toInt(),
                    productName,
                    price.toFloat(),
                    salePrice.toFloat(),
                    category.id
                )
                appDataProvider.db.collection("products").document().set(inventory)
                    .addOnSuccessListener {
                        clearFields()
                        isLoading.set(false)
                        _event.postValue(LDAddProductEvent.OnSuccess)
                    }
                    .addOnFailureListener { e ->
                        isLoading.set(false)
                        e.message?.let {
                            _event.postValue(LDAddProductEvent.OnFailure(it))
                        }
                    }
            } else {
                _event.postValue(LDAddProductEvent.FieldsAreEmpty)
            }
        }

    }

    fun showCategoryFragment() {
        showContent.set(false)
        _event.postValue(LDAddProductEvent.ShowCategoryFragment)
    }

    fun onBackgroundViewClick() {
        showContent.set(true)
        _event.postValue(LDAddProductEvent.OnBackgroundViewClicked)
    }

    private fun clearFields() {
        productName.set("")
        available.set("")
        price.set("")
        salePrice.set("")
        categoryProduct.set("")
    }
}