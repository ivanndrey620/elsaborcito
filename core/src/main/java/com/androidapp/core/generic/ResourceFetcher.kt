package com.androidapp.core.generic

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
object ResourceFetcher {

    lateinit var application: BaseApplication

    fun init(application: BaseApplication) {
        ResourceFetcher.application = application
    }

    fun getString(@StringRes resId: Int): String = application.getString(resId)

    fun getString(@StringRes resId: Int, value: String): String = application.getString(resId).format(value)

    fun getStringArray(@ArrayRes resId: Int): Array<String> = application.resources?.getStringArray(resId) ?: arrayOf()

    fun getInteger(@IntegerRes resId: Int): Int = application.resources?.getInteger(resId) ?: 0

    fun getIntArray(@ArrayRes resId: Int): IntArray = application.resources?.getIntArray(resId) ?: intArrayOf()

    fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(application, resId)

    fun getDimen(@DimenRes resId: Int): Float = application.resources.getDimension(resId)

    fun getDrawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(application, resId)
}