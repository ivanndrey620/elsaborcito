package com.androidapp.api.persistence.model

import androidx.databinding.ObservableBoolean
import com.google.firebase.firestore.Exclude

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
data class Category(
    @get:Exclude @set:Exclude
    var id: String = "",
    var name: String = ""
) {
}