package com.androidapp.api.persistence.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
@Parcelize
data class Inventory(
    @get:Exclude @set:Exclude
    var id: String = "",
    var stock: Int = 0,
    var name: String = "",
    var price: Float = 0f,
    var salePrice: Float = 0f,
    var category: String = ""
) : Parcelable
