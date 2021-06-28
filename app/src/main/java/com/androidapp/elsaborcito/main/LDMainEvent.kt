package com.androidapp.elsaborcito.main

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
sealed class LDMainEvent {

    object NavigateToLogin: LDMainEvent()

    object NavigateToHome: LDMainEvent()
}