package com.ankursolution.jffmyadmin.ui.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.AllPendingOrdersAdapter
import com.ankursolution.jffmyadmin.data.model.emumeration.EditType
import com.ankursolution.jffmyadmin.databinding.FragmentOrderHistoryBinding
import com.ankursolution.jffmyadmin.databinding.FragmentorderitemfragmentBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_order.*
import javax.inject.Inject

@AndroidEntryPoint
class OrderHistoryFragment:BaseFragment<FragmentOrderHistoryBinding, HomeViewModel>()  {
    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: AllPendingOrdersAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().rvProducts.adapter = adapter
        getDataFromApi()

    }

    private fun getDataFromApi() {
        homeViewModel.getOrderHistory("delivered").observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {
                    adapter.submitList(it.result)
                }
            }
        })
    }


    override fun getLayoutId() = R.layout.fragment_order_history
    override fun getViewModel() = homeViewModel

}