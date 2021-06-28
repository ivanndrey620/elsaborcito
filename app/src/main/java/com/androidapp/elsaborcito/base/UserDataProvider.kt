package com.androidapp.elsaborcito.base

import com.androidapp.core.generic.Pref

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class UserDataProvider {

    fun isLoggedIn(): Boolean = Pref.getLogIn()

}