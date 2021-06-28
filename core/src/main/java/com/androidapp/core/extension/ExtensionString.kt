package com.androidapp.core.extension

import android.util.Patterns

/**
 *  Created by Iván Bolaños on 04/06/2021
 */
fun String.isEmailAddress(): Boolean = isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()