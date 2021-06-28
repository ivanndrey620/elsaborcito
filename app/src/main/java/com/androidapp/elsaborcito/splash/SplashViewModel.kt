package com.androidapp.elsaborcito.splash

import androidx.lifecycle.viewModelScope
import com.androidapp.core.viewModel.ScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class SplashViewModel : ScreenViewModel() {

    override fun onBindView() {
        super.onBindView()
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
        }
    }
}