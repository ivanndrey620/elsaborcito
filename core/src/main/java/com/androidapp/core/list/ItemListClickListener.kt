package com.androidapp.core.list

/**
 * Created by Pablo Reyes [devpab@gmail.com] on 10/16/18.
 */
interface ItemListClickListener<T> {

    fun onItemListClickListener(item: T, position: Int)
}