package com.androidapp.elsaborcito.sale.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.androidapp.elsaborcito.new_sale.NewSaleFragment
import com.androidapp.elsaborcito.sale_confirmation.SaleConfirmationFragment

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SalePagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return if (position == NEW_SALE_PAGE) NewSaleFragment() else SaleConfirmationFragment()
    }


    companion object {
        const val NEW_SALE_PAGE = 0
        const val SALE_CONFIRMATION_PAGE = 1
    }
}