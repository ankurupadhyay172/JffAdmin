package com.ankursolution.jffmyadmin.ui.takeorders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.AddToCartRequestModel
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.AddToCartProductsAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.ProductsAdapter
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.databinding.FragmentShowCategoryBinding
import com.ankursolution.jffmyadmin.databinding.FragmentShowProductBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TakeOrdersProductsFragment : BaseFragment<FragmentShowProductBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()
    val args: TakeOrdersProductsFragmentArgs by navArgs()
    @Inject
    lateinit var adapter: AddToCartProductsAdapter
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvProducts.adapter = adapter
        getProductsData()

    }

    private fun getProductsData() {
        homeViewModel.getProduct(CommonRequestModel(args.catId)).observe(viewLifecycleOwner, {
            it?.getValueOrNull().let {

                if (it?.status==1)
                {
                    it.result.let {
                        adapter.submitList(it)

                        adapter.addToCartClick = {pid,vid,quan->
                        homeViewModel.addToCart(AddToCartRequestModel(sessionManager.getUserAuth()?.userid,pid,vid,sessionManager.getUserAuth()?.userid,quan = quan.toString(),type = "add")).observe(viewLifecycleOwner,
                            {
                            it.getValueOrNull()?.let {
                                if (it.status==1)
                                {
                                    showToast("successfully added into cart")
                                }
                            }
                            })
                        }


                        adapter.updateCart = {pid,vid,quan->
                            var type = if (quan<1)
                                "delete"
                            else "update"
                            homeViewModel.addToCart(AddToCartRequestModel(sessionManager.getUserAuth()?.userid,pid,vid,sessionManager.getUserAuth()?.userid,quan = quan.toString(),type = type)).observe(viewLifecycleOwner,
                                {
                                    it.getValueOrNull()?.let {
                                        if (it.status==1)
                                        {
                                            showToast("Cart updated successfully")

                                        }
                                    }
                                })
                        }

                    }
                }

            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_show_product
    override fun getViewModel() = homeViewModel


}