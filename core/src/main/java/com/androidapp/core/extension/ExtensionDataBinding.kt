package com.androidapp.core.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by preyes@phunware.com (Pablo Reyes) on 4/30/18.
 */
fun <T : ViewDataBinding> ViewGroup.bind(layoutRes: Int, attachToParent: Boolean = false): T {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, attachToParent)
}
