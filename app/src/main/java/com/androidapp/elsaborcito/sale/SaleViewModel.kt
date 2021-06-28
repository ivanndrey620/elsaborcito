package com.androidapp.elsaborcito.sale

import androidx.databinding.ObservableBoolean
import com.androidapp.core.viewModel.ScreenViewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleViewModel : ScreenViewModel() {

    val isPagerGeneralOptionSelected = ObservableBoolean(true)


    fun onViewPagerChange(page: Int) {
        isPagerGeneralOptionSelected.set(page == 0)
    }

}