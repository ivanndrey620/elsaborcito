package com.androidapp.api.persistence.model

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
data class TodaySale(
    var id: String = "",
    var inventoryList: MutableList<Inventory> = mutableListOf()
)
