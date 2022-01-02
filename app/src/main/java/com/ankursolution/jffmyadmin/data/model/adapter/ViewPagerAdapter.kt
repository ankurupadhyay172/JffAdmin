package com.ankursolution.jffmyadmin.data.model.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ankursolution.jffmyadmin.ui.HomePagerFragment

class ViewPagerAdapter(val list: Array<String>,context:Fragment):FragmentStateAdapter(context) {
    override fun getItemCount() = list.size

    override fun createFragment(position: Int) = HomePagerFragment.getInstance(list[position])

}