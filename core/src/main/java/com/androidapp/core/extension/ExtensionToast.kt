package com.androidapp.core.extension

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by preyes@phunware.com (Pablo Reyes) on 4/30/18.
 */

/** Toast - Resource Id **/

fun Fragment.toast(resourceId: Int) = toast(getString(resourceId))
fun Fragment.longToast(resourceId: Int) = longToast(getString(resourceId))

fun Context.toast(resourceId: Int) = toast(getString(resourceId))
fun Context.longToast(resourceId: Int) = longToast(getString(resourceId))

/** Toast - CharSequence **/

fun Context.toast(message: CharSequence?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context.longToast(message: CharSequence?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Fragment.toast(message: CharSequence?) = context?.toast(message)
fun Fragment.longToast(message: CharSequence?) = context?.longToast(message)