package com.androidapp.core.extension

import java.text.SimpleDateFormat
import java.util.Date

/**
 *  Created by Iván Bolaños on 18/06/2021
 */
fun String.toDate(format: String): Date = SimpleDateFormat(format).parse(this)

fun Date.applyFormat(format: String): String = SimpleDateFormat(format).format(this)

fun Date.getToday(format: String): String = SimpleDateFormat(format).format(this)