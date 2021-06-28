package com.androidapp.core.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
abstract class BaseViewModel: ViewModel(), CoroutineScope {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## Coroutine
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    protected val pendingJobs = Job()

    /**
     * Creates a coroutine-exception handler in order to avoid app-crash events;
     * and handle thrown errors if is required
     */
    protected val coroutineErrorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.e("coroutine-exception, context=$coroutineContext, error=$throwable")
        onCoroutineException(coroutineContext, throwable)
    }

    /**
     * Coroutines dispatched to avoid memory leaks
     */
    override val coroutineContext: CoroutineContext
        get() = (Dispatchers.Default + pendingJobs + coroutineErrorHandler)

    /**
     * override this method to handle errors that have been thrown by a coroutine
     */
    protected open fun onCoroutineException(context: CoroutineContext, error: Throwable) {
        Timber.e("coroutine-exception=$error")
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * ## Rx - Disposable
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    protected val disposable = CompositeDisposable()

    /**
     * Will kill Rx processes to avoid memory leaks when onDestroy|onViewDestroyed is reached
     */
    protected fun Disposable.bindToLifecycle() = apply {
        disposable.add(this)
    }
}