package com.androidapp.elsaborcito.select_category.adapter

import com.androidapp.api.persistence.model.Category
import com.androidapp.core.list.ItemListClickListener
import com.androidapp.core.viewModel.ListItemViewModel

/**
 *  Created by Iván Bolaños on 26/06/2021
 */
class SelectCategoryListItemViewModel(val item: Category, val position: Int, val listener: ItemListClickListener<Category>): ListItemViewModel()