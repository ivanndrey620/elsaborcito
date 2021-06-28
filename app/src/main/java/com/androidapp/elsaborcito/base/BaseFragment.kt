package com.androidapp.elsaborcito.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androidapp.core.BR
import com.androidapp.core.viewModel.ScreenViewModel
import com.androidapp.elsaborcito.base.navigation.Navigator
import org.koin.android.ext.android.inject

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    open var binding: VDB? = null
    open var root: View? = null

    protected abstract val viewModel: ScreenViewModel
    protected val userDataProvider: UserDataProvider by inject()
    protected val navigator: Navigator by lazy { Navigator(findNavController(), userDataProvider) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)

            binding?.lifecycleOwner = this
            root = binding?.root

        }

        viewModel.onStart(savedInstanceState)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.let {
            it.setVariable(BR.viewModel, viewModel)
            onDataBindingViewReady(it)
        }

        // notify view-model that view is ready
        viewModel.onBindView()

    }

    open fun getViewModelFactory(): ViewModelProvider.Factory? = null

    //region life-cycle methods; keep view model notified

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onDestroyView() {
        if (root?.parent != null){
            (root?.parent as ViewGroup).removeAllViews()
        }
        super.onDestroyView()
    }


    //endregion

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## Abstract Methods
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * @return Fragment layout Id (e.g. R.layout.may_fragment)
     */
    @LayoutRes
    abstract fun layoutRes(): Int

    /**
     * Called when ViewModel has already bind. Setup all the UI stuff here
     */
    @UiThread
    protected abstract fun onDataBindingViewReady(binding: VDB)

}