package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.databinding.FragmentSingleProductBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleProductFragment :BaseFragment<FragmentSingleProductBinding,HomeViewModel>(){

    val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    override fun getLayoutId() = R.layout.fragment_single_product


    override fun getViewModel() = homeViewModel
}