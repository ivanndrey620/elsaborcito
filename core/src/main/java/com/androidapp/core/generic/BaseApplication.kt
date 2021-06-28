package com.androidapp.core.generic

import androidx.multidex.MultiDexApplication

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
abstract class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Pref.init(this)
        ResourceFetcher.init(this)
    }
}