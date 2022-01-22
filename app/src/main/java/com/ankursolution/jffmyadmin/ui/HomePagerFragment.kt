package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.AddToCartProductsAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.AllPendingOrdersAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentHomePagerBinding
import com.ankursolution.jffmyadmin.retrofit.LoadState
import com.ankursolution.jffmyadmin.retrofit.LoadingState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomePagerFragment:BaseFragment<FragmentHomePagerBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()
    @Inject
    lateinit var adapter: AllPendingOrdersAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var order_status = arguments?.getString(HOME)
        getViewDataBinding().homeviewmodel = homeViewModel

        getOrders(order_status)



    }

    private fun getOrders(str:String?) {

        getViewDataBinding().rvOrders.adapter = adapter

        homeViewModel.getAllPendingOrders(str?.toLowerCase()).observe(viewLifecycleOwner, {
            it.getValueOrNull()?.let {
                homeViewModel.loadState.postValue(LoadingState.Loaded)

                if (it.status==1)
               {

                   adapter.updateState ={id,status->
                       homeViewModel.updateOrder(OrderUpdateRequestModel(id = id,status =status)).observe(viewLifecycleOwner,
                           {
                           it.getValueOrNull().let {

                                 showToast("Status update successfully"+it?.status)

                           }
                           })
                   }
                adapter.submitList(it.result)

               }else getViewDataBinding().isVisible = true
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