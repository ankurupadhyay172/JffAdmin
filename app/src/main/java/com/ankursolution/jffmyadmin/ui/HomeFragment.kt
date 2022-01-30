package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding,HomeViewModel>(){

    val homeViewModel: HomeViewModel by viewModels()
    lateinit var order_types:Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        order_types = resources.getStringArray(R.array.order_types)

        updateFragment()
        updateData()
        subscribeNotification()


    }

    private fun subscribeNotification() {
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.NOTIFICATION_CHANNEL).addOnSuccessListener { }
    }
    fun updateFragment()
    {
        val adapter = ViewPagerAdapter(order_types,this)
        getViewDataBinding().viewPager2.adapter = adapter
    }


    fun updateData()
    {
        TabLayoutMediator(getViewDataBinding().tabLayout,getViewDataBinding().viewPager2){tab,position->
            tab.text = order_types[position]
        }.attach()

    }


    override fun getLayoutId() = R.layout.fragment_home


    override fun getViewModel() = homeViewModel
}