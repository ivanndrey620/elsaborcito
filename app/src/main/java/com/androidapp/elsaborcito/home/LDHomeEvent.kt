package com.androidapp.elsaborcito.home

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
sealed class LDHomeEvent {

    object OnInventoryParentClick: LDHomeEvent()
    object OnSettingsParentClick: LDHomeEvent()
    object OnAddButtonClick: LDHomeEvent()
    object OnNewSaleParentClick: LDHomeEvent()
}