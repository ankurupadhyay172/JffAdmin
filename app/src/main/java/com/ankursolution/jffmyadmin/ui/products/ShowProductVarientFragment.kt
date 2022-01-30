package com.ankursolution.jffmyadmin.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.ProductVarientRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.ProductVarientAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.ProductsAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentShowCategoryBinding
import com.ankursolution.jffmyadmin.databinding.FragmentShowProductBinding
import com.ankursolution.jffmyadmin.databinding.FragmentShowProductVarientBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowProductVarientFragment : BaseFragment<FragmentShowProductVarientBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()
    val args: ShowProductVarientFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: ProductVarientAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvProducts.adapter = adapter
        getProductsData()
        updateProductVarient()

    }

    private fun updateProductVarient() {

        adapter.update = {
            findNavController().navigate(ShowProductVarientFragmentDirections.actionShowProductVarientFragmentToAddProductVarientsFragment(args.pid,it))
        }

        adapter.delete ={it,url->
            try {
                val photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(url!!)
                photoRef.delete().addOnSuccessListener {s->
                    deleteItem(it)
                }.addOnFailureListener {e->

                    deleteItem(it)
                }

            }catch (e:Exception){
                deleteItem(it)
            }
        }



    }

    private fun deleteItem(it: String?) {
        homeViewModel.updateProductVarient(ProductVarientRequestModel(id=it,type = Constants.DELETE_REQUEST)) .observe(viewLifecycleOwner,{
            it.getValueOrNull().let {
                if (it?.status==1)
                {
                    showToast("Varient deleted successfully")
                    getProductsData()
                }
            }
        })
    }

    private fun getProductsData() {

        homeViewModel.readProductVarient(CommonRequestModel(id = args.pid)).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {
                   // if (it.result.isNullOrEmpty().not())
                    adapter.submitList(it.result)
                }
            }
        })

        getViewDataBinding().addFloating.setOnClickListener {
            findNavController().navigate(ShowProductVarientFragmentDirections.actionShowProductVarientFragmentToAddProductVarientsFragment(args.pid))
        }
    }

    override fun getLayoutId() = R.layout.fragment_show_product_varient
    override fun getViewModel() = homeViewModel


}