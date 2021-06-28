package com.androidapp.core.viewModel

import android.os.Bundle

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
abstract class ScreenViewModel: BaseViewModel() {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    * ## LifeCycle
    * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * Starts the presentation. This should be called in the view's (Activity or Fragment)
     * onCreate() or onViewStatedRestored() method respectively.
     *
     * @param savedInstanceState the saved instance state that contains state saved in
     * [.onSaveInstanceState]
     */
    open fun onStart(savedInstanceState: Bundle?) {}

    /**
     * This should be called in the view's (Activity or Fragment)
     * setContentView() method.
     */
    open fun onBindView() { /* nop */ }

    /**
     * Resumes the presentation. This should be called in the view's (Activity or Fragment)
     * onResume() method.
     */
    open fun onResume() { /* nop */ }

    /**
     * Pauses the presentation. This should be called in the view's (Activity or Fragment)
     * onPause() method.
     */
    open fun onPause() { /* nop */ }

    /**
     * Save the state of the presentation (if any). This should be called in the view's
     * (Activity or Fragment) onSaveInstanceState().
     *
     * @param outState the out state to save instance state
     */
    open fun onSaveInstanceState(outState: Bundle?) { /* nop */ }

    /**
     * Stops the presentation. This should be called in the view's (Activity or Fragment)
     * onStop() method
     */
    open fun onStop() { /* nop */ }

    /**
     * Ends the presentation. This should be called in the view's (Activity or Fragment)
     * onDestroy() or onDestroyView() method respectively.
     */
    open fun onDestroy() {
        pendingJobs.cancel()
        disposable.clear()
    }
}