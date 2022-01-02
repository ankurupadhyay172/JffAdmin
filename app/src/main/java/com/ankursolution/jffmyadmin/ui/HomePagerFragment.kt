package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.adapter.AllPendingOrdersAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentHomePagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePagerFragment:BaseFragment<FragmentHomePagerBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var order_status = arguments?.getString(HOME)

        getOrders(order_status)



    }

    private fun getOrders(str:String?) {
        var adapter = AllPendingOrdersAdapter()
        getViewDataBinding().rvOrders.adapter = adapter

        homeViewModel.getAllPendingOrders(str?.toLowerCase()).observe(viewLifecycleOwner, Observer {
            it.getValueOrNull()?.let {
               if (it.status==1)
               {
                adapter.submitList(it.result)
               }
            }
        })
    }


    companion object{
        const val HOME = "home"
        fun getInstance(status:String?):HomePagerFragment{
            return HomePagerFragment().apply {
            arguments = bundleOf(HOME to status)
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_home_pager


    override fun getViewModel() = homeViewModel
}