package com.androidapp.elsaborcito.main

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.UserDataProvider
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class MainViewModel(private val userDataProvider: UserDataProvider): ScreenViewModel(), LoadingState {

    override val isLoading: ObservableBoolean = ObservableBoolean()

    private val _event = SingleLiveEvent<LDMainEvent>()
    val event: LiveData<LDMainEvent> = _event

    override fun onBindView() {
        super.onBindView()
        startMainNavigation()
    }

    private fun startMainNavigation() {
        viewModelScope.launch(coroutineErrorHandler) {
            if (userDataProvider.isLoggedIn()) {
                _event.postValue(LDMainEvent.NavigateToHome)
            } else {
                _event.postValue(LDMainEvent.NavigateToLogin)
            }
        }
    }

    override fun onCoroutineException(context: CoroutineContext, error: Throwable) {
        super.onCoroutineException(context, error)
        isLoading.set(false)
        Log.d("AndyTag", "Main View Model Error $error")
    }
}