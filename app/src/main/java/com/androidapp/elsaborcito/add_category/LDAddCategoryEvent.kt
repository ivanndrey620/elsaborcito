package com.androidapp.elsaborcito.add_category

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
sealed class LDAddCategoryEvent {

    object OnSuccess: LDAddCategoryEvent()
    object OnCloseFragment: LDAddCategoryEvent()
    class OnFailure(val message: String?): LDAddCategoryEvent()
}