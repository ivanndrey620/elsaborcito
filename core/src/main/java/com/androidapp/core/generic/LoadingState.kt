package com.androidapp.core.generic

import androidx.databinding.ObservableBoolean

/**
 *  Created by Iván Bolaños on 02/06/2021
 */
interface LoadingState {

    /**
     * true: view is in freeze state and progress bar is visible
     * false: view is active and progress bar is hidden
     */
    val isLoading: ObservableBoolean
}