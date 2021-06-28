package com.androidapp.elsaborcito.base.extension

import androidx.fragment.app.FragmentManager

/**
 * Created by Pablo Reyes [devpab@gmail.com] on 2019-07-31.
 */

/** remove fragment if it's attached **/
fun FragmentManager.hide(tag: String) {
    findFragmentByTag(tag)?.let { fragment ->
        beginTransaction().remove(fragment).commitAllowingStateLoss()
    }
}

fun FragmentManager.clear() {
    for (position in 0 until backStackEntryCount) {
        val fragmentId : Int  = getBackStackEntryAt(position).id

        findFragmentById(fragmentId)?.let { fragment ->
            beginTransaction().remove(fragment).commitAllowingStateLoss()
        }
    }
}