package com.androidapp.elsaborcito.sale

import androidx.viewpager.widget.ViewPager
import com.androidapp.elsaborcito.R
import com.androidapp.elsaborcito.base.BaseFragment
import com.androidapp.elsaborcito.databinding.FragmentSaleBinding
import com.androidapp.elsaborcito.sale.adapter.SalePagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  Created by Iván Bolaños on 24/06/2021
 */
class SaleFragment: BaseFragment<FragmentSaleBinding>() {
    override val viewModel: SaleViewModel by viewModel()

    override fun layoutRes(): Int = R.layout.fragment_sale

    override fun onDataBindingViewReady(binding: FragmentSaleBinding) {
        // setup view-pager
        binding.viewPager.adapter = SalePagerAdapter(childFragmentManager)
        binding.viewPager.isSaveEnabled = false
        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { /* nop */ }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { /* nop */ }

            override fun onPageSelected(position: Int) = viewModel.onViewPagerChange(position)
        })

        selectViewPagerPage(binding.viewPager, true)
    }

    private fun selectViewPagerPage(viewPager: ViewPager, isGeneralTabSelected: Boolean) {
        viewPager.setCurrentItem(if (isGeneralTabSelected) {
            SalePagerAdapter.NEW_SALE_PAGE
        } else {
            SalePagerAdapter.SALE_CONFIRMATION_PAGE
        }, true)
    }
}