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
import com.ankursolution.jffmyadmin.data.model.SingleOrderResult
import com.ankursolution.jffmyadmin.data.model.adapter.OrderItemAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentSingleOrderBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Common
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_order.*
import javax.inject.Inject

@AndroidEntryPoint
class SingleOrderFragment:BaseFragment<FragmentSingleOrderBinding, HomeViewModel>(),View.OnClickListener  {
    val homeViewModel: HomeViewModel by viewModels()
    val args: SingleOrderFragmentArgs by navArgs()

    @Inject
    lateinit var adapter:OrderItemAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvItems.adapter = adapter

        getOrder(args.orderId)
        handleDelivery()
        handlePayment()

    }



    private fun getOrder(str:String?) {

        homeViewModel.getSinglePendingOrders(str).observe(viewLifecycleOwner, Observer {
            it.getValueOrNull()?.let {


                try {
                    getViewDataBinding().model = it.result.get(0)
                    var total_cart = Common.setPrice(it.items.sumOf {item->
                        item.product_price.toDouble()*item.quan.toInt() }.toString())
                    getViewDataBinding().cartTotal.text = total_cart
                    getViewDataBinding().totalCost.text = total_cart

                    var total_amount = it.items.sumOf {item->
                        item.product_price.toDouble()*item.quan.toInt() }


                    if (it.result[0].delivery_charge.isNullOrEmpty().not())
                        total_amount += it.result[0].delivery_charge!!.toInt()

                    if (it.result[0].discount.isNullOrEmpty().not())
                    total_amount -= it.result[0].discount!!.toInt()
                    getViewDataBinding().total.text = Common.setPrice(total_amount.toString())
                    getViewDataBinding().totalItem.text = it.items.size.toString()
                }catch (e:Exception){e.printStackTrace()}
                adapter.submitList(it.items)

                if (it.result.isNullOrEmpty().not())
                handleEditOptions(it.result[0])
            }
        })
    }

    private fun handleEditOptions(result: SingleOrderResult.Result) {


        getViewDataBinding().liAddress.setOnClickListener {v->

                findNavController().navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToEditOrderItemsFragment(address = result.delivery_address?:"",orderId = args.orderId,type=getString(R.string.address)))

        }
        getViewDataBinding().liInstruction.setOnClickListener {v->
                findNavController().navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToEditOrderItemsFragment(instruction = result.user_instruction?:"",orderId = args.orderId,type=getString(R.string.user_instruction)))

        }


        getViewDataBinding().liTableno.setOnClickListener {v->
                findNavController().navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToEditOrderItemsFragment(tableno = result.table_no?:"",orderId = args.orderId,type=getString(R.string.table_no)))

        }

        getViewDataBinding().edtDiscount.setOnClickListener {v->
                findNavController().navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToEditOrderItemsFragment(discount = result.discount?:"",orderId = args.orderId,type=getString(R.string.discount)))

        }
    }

    private fun handleDelivery() {

        getViewDataBinding().rbdelivery.setOnClickListener(this)
        getViewDataBinding().rbtakeaway.setOnClickListener(this)
        getViewDataBinding().lidelivery.setOnClickListener(this)
        getViewDataBinding().litakeaway.setOnClickListener(this)
        getViewDataBinding().lidelivery2.setOnClickListener(this)
        getViewDataBinding().litakeaway2.setOnClickListener(this)

    }



    private fun handlePayment() {

        getViewDataBinding().rbCash.setOnClickListener(this)
        getViewDataBinding().rbPayCash.setOnClickListener(this)
        getViewDataBinding().liCash.setOnClickListener(this)
        getViewDataBinding().liPayCash.setOnClickListener(this)
        getViewDataBinding().liPayOnline.setOnClickListener(this)
    }




    override fun getLayoutId() = R.layout.fragment_single_order


    override fun getViewModel() = homeViewModel
    override fun onClick(p0: View?) {
        when(p0)
        {
            getViewDataBinding().rbdelivery,
            getViewDataBinding().lidelivery,
            getViewDataBinding().lidelivery2->{
                getViewDataBinding().rbdelivery.isChecked = true
                getViewDataBinding().rbtakeaway.isChecked = false}

            getViewDataBinding().rbtakeaway,
            getViewDataBinding().litakeaway,
            getViewDataBinding().litakeaway2->{
                getViewDataBinding().rbdelivery.isChecked = false
                getViewDataBinding().rbtakeaway.isChecked = true
            }


            getViewDataBinding().liCash,getViewDataBinding().rbCash->{
                getViewDataBinding().rbPayCash.isChecked = false
                getViewDataBinding().rbPayOnline.isChecked = false
                getViewDataBinding().rbCash.isChecked = true
            }

            getViewDataBinding().liPayCash,getViewDataBinding().rbPayCash->{
              getViewDataBinding().rbPayCash.isChecked = true
                getViewDataBinding().rbPayOnline.isChecked = false
                getViewDataBinding().rbCash.isChecked = false
            }




            getViewDataBinding().liPayOnline,getViewDataBinding().rbPayOnline->{
                getViewDataBinding().rbPayCash.isChecked = false
                getViewDataBinding().rbPayOnline.isChecked = true
                getViewDataBinding().rbCash.isChecked = false
            }
        }
    }
}