package com.androidapp.elsaborcito.base

import androidx.databinding.ObservableBoolean
import com.androidapp.api.persistence.model.Category

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
class CheckItem(val data: Category, flag: Boolean) {

    val isChecked: ObservableBoolean = ObservableBoolean(flag)



    fun toggle() {
        isChecked.set(isChecked.get().not())
    }
}