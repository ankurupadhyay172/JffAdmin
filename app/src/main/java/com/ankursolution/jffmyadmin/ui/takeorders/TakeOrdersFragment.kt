package com.ankursolution.jffmyadmin.ui.takeorders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryHomeAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentTakeOrdersBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TakeOrdersFragment : BaseFragment<FragmentTakeOrdersBinding,HomeViewModel>() {


    val homeViewModel: HomeViewModel by viewModels()
    @Inject
    lateinit var adapter:CategoryHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().rvCategory.adapter = adapter
        getCategoryData()
    }

    private fun getCategoryData() {
        homeViewModel.getCategory().observe(viewLifecycleOwner, {
            it.getValueOrNull()?.let {
            if (it.status==1)
            {
                adapter.submitList(it.result)
            }
            }
        })
    }


    override fun getLayoutId() = R.layout.fragment_take_orders


    override fun getViewModel() = homeViewModel


}