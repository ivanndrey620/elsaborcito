package com.androidapp.elsaborcito.select_category

import com.androidapp.api.persistence.model.Category

/**
 *  Created by Iván Bolaños on 26/06/2021
 */
sealed class LDSelectCategoryEvent {

    class SetListAdapter(val list: List<Category>): LDSelectCategoryEvent()

    class OnCategorySelected(val category: Category): LDSelectCategoryEvent()

    object NavigateToAddCategory: LDSelectCategoryEvent()
}