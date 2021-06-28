package com.androidapp.elsaborcito.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.androidapp.core.databinding.OkKeyboardClickListener
import com.androidapp.core.generic.LoadingState
import com.androidapp.core.generic.Pref
import com.androidapp.core.livedata.SingleLiveEvent
import com.androidapp.core.viewModel.ScreenViewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class LoginViewModel : ScreenViewModel(), LoadingState {

    override val isLoading: ObservableBoolean = ObservableBoolean()

    private val _event = SingleLiveEvent<LDLoginEvent>()
    val event: LiveData<LDLoginEvent> = _event

    val username = ObservableField<String>()
    val password = ObservableField<String>()

    val okKeyboardClickListener: OkKeyboardClickListener = object : OkKeyboardClickListener {
        override fun onOkKeyboardClick() = onLogInButtonClick()
    }

    fun onForgotPasswordButtonClick() {

    }

    fun onLogInButtonClick() {
        val userName = username.get() ?: ""
        val password = password.get() ?: ""

        if (userName.isNotEmpty() && password.isNotEmpty()) {
            if (userName == "andy" && password == "12345") {
                Pref.setLogIn(true)
                _event.postValue(LDLoginEvent.NavigateToHome)
            } else {
                _event.postValue(LDLoginEvent.OnError("No coinciden"))
            }
        }
    }

    fun onRegisterButtonClick() {

    }

}