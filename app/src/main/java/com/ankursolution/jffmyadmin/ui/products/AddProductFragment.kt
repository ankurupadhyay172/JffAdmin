package com.ankursolution.jffmyadmin.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.ProductModel
import com.ankursolution.jffmyadmin.data.model.ProductRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentAddProductBinding
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment :BaseFragment<FragmentAddProductBinding,HomeViewModel>(){

    val homeViewModel: HomeViewModel by viewModels()
    val args:AddProductFragmentArgs by navArgs()

    var model:ProductModel.Result? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createProduct()
        setData()


    }

    private fun setData() {
        if(args.model!="na")
        {
            model = Gson().fromJson(args.model,ProductModel.Result::class.java)
            getViewDataBinding().model = model
        }
    }

    private fun createProduct() {

        getViewDataBinding().submit.setOnClickListener {
            if (getViewDataBinding().edtProductName.text.isNullOrEmpty())
            {
                getViewDataBinding().edtProductName.setError("Please enter product name")
            }else {
                getViewDataBinding().submit.visibility = View.GONE
                getViewDataBinding().progressBar.visibility = View.VISIBLE

                if (model!=null)
                {
                    homeViewModel.updateProduct(ProductRequestModel(id = model?.id,product_name = model?.productName,product_description = model?.productDescription)).observe(viewLifecycleOwner,{
                        it.getValueOrNull()?.let {
                            if (it.status==1)
                            {
                                showToast("Updated Successfully")
                                findNavController().popBackStack()
                            }
                        }
                    })
                }else{
                    homeViewModel.createProduct(
                        ProductRequestModel(
                            args.catId,
                            getViewDataBinding().edtProductName.text.toString(),
                            getViewDataBinding().edtDescription.text.toString()
                        )
                    ).observe(viewLifecycleOwner, {
                        it.getValueOrNull()?.let {
                            if (it.status == 1) {
                                showToast("Product created successfully")
                                findNavController().popBackStack()
                            }else{
                                showToast("Try Again !Somthing went wrong")
                                getViewDataBinding().submit.visibility = View.VISIBLE
                                getViewDataBinding().progressBar.visibility = View.GONE
                            }
                        }
                    })
                }
            }
        }
    }


    override fun getLayoutId() = R.layout.fragment_add_product


    override fun getViewModel() = homeViewModel
}