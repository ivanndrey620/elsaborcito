package com.androidapp.elsaborcito.categories

import com.androidapp.api.persistence.model.Category

/**
 *  Created by Iván Bolaños on 25/06/2021
 */
sealed class LDCategoriesEvent {

    class SetListAdapter(val list: List<Category>): LDCategoriesEvent()
    object OnAddButtonClick: LDCategoriesEvent()
    object OnBackgroundViewClicked: LDCategoriesEvent()
}