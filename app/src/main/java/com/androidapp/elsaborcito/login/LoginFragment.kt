package com.androidapp.elsaborcito.login

import com.androidapp.core.extension.toast
import com.androidapp.core.livedata.observe
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.base.extension.hideKeyboard
import com.androidapp.elsaborcito.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_login

    override fun onDataBindingViewReady(binding: FragmentLoginBinding) {

        observe(viewModel.event) { event ->
            when (event) {
                is LDLoginEvent.NavigateToHome -> {
                    navigator.navigateToHome()
                    binding.inputPassword.hideKeyboard()
                }
                is LDLoginEvent.OnError -> toast("Error ${event.message}")
            }
        }
    }
}