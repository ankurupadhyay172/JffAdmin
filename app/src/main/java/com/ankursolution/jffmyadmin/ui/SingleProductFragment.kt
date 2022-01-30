package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.Varients
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.databinding.FragmentSingleProductBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.ankursolution.jffmyadmin.ui.products.ShowProductsFragmentArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleProductFragment :BaseFragment<FragmentSingleProductBinding,HomeViewModel>(){

    val homeViewModel: HomeViewModel by viewModels()
    val args: SingleProductFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSingleProduct()



    }

    private fun getSingleProduct() {

        homeViewModel.getProductDetails(CommonRequestModel(id =args.pid)).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {
                    val selected = it.result[0].varients.filter { it.varientName==args.varient }
                    getViewDataBinding().model = selected[0]
                }
            }
        })
    }


    override fun getLayoutId() = R.layout.fragment_single_product


    override fun getViewModel() = homeViewModel
}