package com.androidapp.elsaborcito

import com.androidapp.core.generic.BaseApplication
import com.androidapp.core.generic.Pref
import com.androidapp.core.generic.ResourceFetcher
import com.androidapp.elsaborcito.base.AppKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class ElSaborcitoApp: BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        // start database, resource-fetcher and shared-preferences
        Pref.init(this)
        ResourceFetcher.init(this)
        // start koin-di
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ElSaborcitoApp)
            modules(AppKoinModules.appModule)
        }

    }
}