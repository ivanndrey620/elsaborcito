package com.androidapp.elsaborcito.splash

import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val viewModel: SplashViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun onDataBindingViewReady(binding: FragmentSplashBinding) {
    }
}