package com.androidapp.core.activity

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.androidapp.core.viewModel.ScreenViewModel
import io.github.inflationx.viewpump.ViewPumpContextWrapper

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
abstract class BaseActivity<VDB : ViewDataBinding>: AppCompatActivity() {

    open var binding: VDB? = null

    protected abstract val viewModel: ScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.onStart(savedInstanceState)
        super.onCreate(savedInstanceState)

        if (binding == null) {
            binding = DataBindingUtil.setContentView(this, layoutRes())
            binding?.let { onDataBindingViewReady(it) }
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        viewModel.onBindView()
    }

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

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

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