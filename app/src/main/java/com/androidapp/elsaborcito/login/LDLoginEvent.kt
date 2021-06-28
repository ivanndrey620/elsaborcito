package com.androidapp.elsaborcito.login

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDLoginEvent {

    object NavigateToHome: LDLoginEvent()

    class OnError(val message: String): LDLoginEvent()
}