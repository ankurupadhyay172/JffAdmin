package com.ankursolution.jffmyadmin.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.ProductRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.ProductsAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentShowCategoryBinding
import com.ankursolution.jffmyadmin.databinding.FragmentShowProductBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowProductsFragment : BaseFragment<FragmentShowProductBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()
    val args: ShowProductsFragmentArgs by navArgs()


    @Inject
    lateinit var adapter: ProductsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvProducts.adapter = adapter
        getProductsData()
        deleteItem()

    }

    private fun deleteItem() {
        adapter.update = {
            findNavController().navigate(ShowProductsFragmentDirections.actionShowProductsFragmentToAddProductFragment(args.catId,it))
        }

        adapter.delete = {
            homeViewModel.updateProduct(ProductRequestModel(id = it,type = Constants.DELETE_REQUEST)).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        showToast("Product Deleted Successfully")
                        getProductsData()
                    }else showToast("Somthing went wrong please try again later")
                }
            })
        }
    }

    private fun getProductsData() {
        homeViewModel.getProduct(CommonRequestModel(args.catId)).observe(viewLifecycleOwner, {
            it?.getValueOrNull().let {


                getViewDataBinding().isVisible = it?.status==null

                if (it?.status==1)
                {

                    it.result.let {

                        adapter.submitList(it)
                    }
                }else{
                    getViewDataBinding().isVisible = true

                }

            }
        })

        getViewDataBinding().addFloating.setOnClickListener {
            findNavController().navigate(ShowProductsFragmentDirections.actionShowProductsFragmentToAddProductFragment(args.catId))
        }
    }



    override fun getLayoutId() = R.layout.fragment_show_product
    override fun getViewModel() = homeViewModel


}