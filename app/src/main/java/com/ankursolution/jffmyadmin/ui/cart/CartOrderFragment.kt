package com.ankursolution.jffmyadmin.ui.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.AddOrderRequestModel
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.data.model.UpdateCartRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.AllPendingOrdersAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.CartProductsAdapter
import com.ankursolution.jffmyadmin.data.model.emumeration.EditType
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.databinding.FragmentCartOrderBinding
import com.ankursolution.jffmyadmin.databinding.FragmentOrderHistoryBinding
import com.ankursolution.jffmyadmin.databinding.FragmentorderitemfragmentBinding
import com.ankursolution.jffmyadmin.retrofit.LoadingState
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Common
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart_order.*
import kotlinx.android.synthetic.main.fragment_single_order.*
import javax.inject.Inject

@AndroidEntryPoint
class CartOrderFragment:BaseFragment<FragmentCartOrderBinding, HomeViewModel>()  {
    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: CartProductsAdapter

    @Inject
    lateinit var sessionManager: SessionManager

    var total:Double =0.0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().rvItems.adapter = adapter
        getViewDataBinding().homeviewmodel = homeViewModel

        getDataFromApi()

    }



    private fun getDataFromApi() {
        homeViewModel.getCartItem(sessionManager.getUserAuth()?.userid).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                homeViewModel.loadState.postValue(LoadingState.Loaded)

                if (it.status==1)
                {
                    total = it.result.sumOf { it.varientPrice.toDouble()*it.quan.toInt() }

                    getViewDataBinding().totalPrice.setText(Common.setPrice(total.toString()))
                    getViewDataBinding().deliveryCharge.setText(Common.setPrice("0"))
                    getViewDataBinding().grandTotal.setText(Common.setPrice(total.toString()))
                    adapter.submitList(it.result)
                    getViewDataBinding().checkout.setOnClickListener {v->

                        if (edtName.text.toString().isEmpty()||edtMobile.text.isEmpty())
                        {
                            if (edtName.text.toString().isEmpty())
                                edtName.setError(getString(R.string.error_name))
                            if (edtMobile.text.isEmpty())
                                edtMobile.setError(getString(R.string.error_mobile))
                        }else{
                            homeViewModel.addOrder(AddOrderRequestModel(edtMobile.text.toString(),edtName.text.toString(),"0",total.toString(),
                                orderType = "table",tableNo = edtTableNo.text.toString(),orderStatus = "pending",products = it.result)).observe(viewLifecycleOwner,
                                {
                                    it.getValueOrNull()?.let {
                                        if (it.status==1)
                                        {

                                          //  findNavController().popBackStack(R.id.homeFragment,true)
                                              findNavController().navigate(CartOrderFragmentDirections.actionCartOrderFragmentToHomeFragment())
                                              showToast(getString(R.string.order_success))
                                        }
                                    }
                                })
                        }
                    }
                }
            }
        })

        adapter.deleteCart = {
            homeViewModel.updateCart(UpdateCartRequestModel(it,"delete")).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        getDataFromApi()

                        showToast("Item Deleted Successfully")
                    }else{
                        showToast("Somthing went wrong")
                    }
                }
            })
        }

        adapter.updateCart = {id,quan->
            homeViewModel.updateCart(UpdateCartRequestModel(id = id,type = "update",quan =quan.toString() )).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {

                    showToast("Quantity Updated Successfully")
                }
            })
        }
    }

    override fun getLayoutId() = R.layout.fragment_cart_order
    override fun getViewModel() = homeViewModel

}