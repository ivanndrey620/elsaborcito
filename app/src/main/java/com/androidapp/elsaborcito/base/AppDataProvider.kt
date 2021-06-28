package com.androidapp.elsaborcito.base

import com.androidapp.api.persistence.model.Category
import com.androidapp.api.persistence.model.Inventory
import com.androidapp.api.persistence.model.TodaySale
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.subjects.BehaviorSubject

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
class AppDataProvider {

     val db = Firebase.firestore

     val todaySales: BehaviorSubject<MutableList<Inventory>> = BehaviorSubject.create()

     val categories: BehaviorSubject<List<Category>> = BehaviorSubject.create()

}