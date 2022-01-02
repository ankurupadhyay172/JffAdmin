package com.ankursolution.jffmyadmin.ui.takeorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.databinding.FragmentTakeOrdersBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel


class TakeOrdersFragment : BaseFragment<FragmentTakeOrdersBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()


    override fun getLayoutId() = R.layout.fragment_take_orders


    override fun getViewModel() = homeViewModel


}