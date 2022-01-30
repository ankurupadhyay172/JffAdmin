package com.ankursolution.jffmyadmin.ui.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.MainActivity
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.OrderItemRequestModel
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.data.model.SingleOrderResult
import com.ankursolution.jffmyadmin.data.model.adapter.OrderItemAdapter
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.databinding.FragmentSingleOrderBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Common
import com.ankursolution.jffmyadmin.utils.ext.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_order.*
import kotlinx.android.synthetic.main.item_khatausers.*
import kotlinx.android.synthetic.main.item_pending_orders.*
import javax.inject.Inject

@AndroidEntryPoint
class SingleOrderFragment:BaseFragment<FragmentSingleOrderBinding, HomeViewModel>(),View.OnClickListener  {
    val homeViewModel: HomeViewModel by viewModels()
    val args: SingleOrderFragmentArgs by navArgs()

    @Inject
    lateinit var adapter:OrderItemAdapter
    @Inject
    lateinit var sessionManager: SessionManager



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvItems.adapter = adapter
        Constants.SELECTED_ORDER = args.orderId
        getOrder(args.orderId)
        handleDelivery()
        handlePayment()
        updateOrderItem()
        checkoutOrder()



    }

    private fun checkoutOrder() {

        getViewDataBinding().checkout.setOnClickListener {
            getViewDataBinding().checkout.visibility = View.GONE
            getViewDataBinding().progressBar.visibility = View.VISIBLE


            homeViewModel.completeJffOrder(OrderUpdateRequestModel(id=args.orderId,status = getString(R.string.order_complete),staff_id =sessionManager.getUserAuth()?.userid )).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        showToast("Order Completed Successfully")
                        findNavController().popBackStack()

                    }else
                    {
                        getViewDataBinding().checkout.visibility = View.VISIBLE
                        getViewDataBinding().progressBar.visibility = View.GONE
                        showToast("Somthing went wrong please try again later")
                    }
                }
            })
        }
    }

    private fun updateOrderItem() {

        adapter.updateCart = {id,quan->

            homeViewModel.updateOrderItem(OrderItemRequestModel(id=id,quan = quan,type = Constants.UPDATE_WITH_ID)).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        showToast("Item Updated Successfully")
                        getOrder(args.orderId)
                    }else showToast("Somthing went wrong please try again later")
                }
            })
        }

        adapter.deleteItem = {
            homeViewModel.updateOrderItem(OrderItemRequestModel(id=it,type = Constants.DELETE_REQUEST)).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        showToast("Item Deleted Successfully")
                        getOrder(args.orderId)
                    }else showToast("Somthing went wrong please try again later")
                }
            })
        }
    }


    private fun getOrder(str:String?) {

        homeViewModel.getSinglePendingOrders(str).observe(viewLifecycleOwner, Observer {
            it.getValueOrNull()?.let {

                try {
                    Constants.USER_ID = it.result.get(0).user_id
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


        add_item.setOnClickListener {
            findNavController().navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToTakeOrdersFragment())
        }
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
        getViewDataBinding().rbtable.setOnClickListener(this)
        getViewDataBinding().lidelivery.setOnClickListener(this)
        getViewDataBinding().litakeaway.setOnClickListener(this)
        getViewDataBinding().litable.setOnClickListener(this)
        getViewDataBinding().lidelivery2.setOnClickListener(this)
        getViewDataBinding().litakeaway2.setOnClickListener(this)
        getViewDataBinding().litable2.setOnClickListener(this)

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
                getViewDataBinding().rbtakeaway.isChecked = false
                getViewDataBinding().rbtable.isChecked = false
                updateOrderType(getString(R.string.delivery))

            }

            getViewDataBinding().rbtakeaway,
            getViewDataBinding().litakeaway,
            getViewDataBinding().litakeaway2->{
                getViewDataBinding().rbdelivery.isChecked = false
                getViewDataBinding().rbtakeaway.isChecked = true
                getViewDataBinding().rbtable.isChecked = false
                updateOrderType(getString(R.string.takeaway))
            }


            getViewDataBinding().rbtable,
            getViewDataBinding().litable,
            getViewDataBinding().litable2->{
                getViewDataBinding().rbtable.isChecked = true
                getViewDataBinding().rbdelivery.isChecked = false
                getViewDataBinding().rbtakeaway.isChecked = false
                updateOrderType(getString(R.string.table))
            }

            getViewDataBinding().liCash,getViewDataBinding().rbCash->{
                getViewDataBinding().rbPayCash.isChecked = false
                getViewDataBinding().rbPayOnline.isChecked = false
                getViewDataBinding().rbCash.isChecked = true
                updatePaymentMethod(getString(R.string.online))
            }

            getViewDataBinding().liPayCash,getViewDataBinding().rbPayCash->{
              getViewDataBinding().rbPayCash.isChecked = true
                getViewDataBinding().rbPayOnline.isChecked = false
                getViewDataBinding().rbCash.isChecked = false
                updatePaymentMethod(getString(R.string.cod))
            }




            getViewDataBinding().liPayOnline,getViewDataBinding().rbPayOnline->{
                getViewDataBinding().rbPayCash.isChecked = false
                getViewDataBinding().rbPayOnline.isChecked = true
                getViewDataBinding().rbCash.isChecked = false
                updatePaymentMethod(getString(R.string.pad))
            }
        }
    }
    fun updatePaymentMethod(payment_type:String)
    {
        homeViewModel.updateOrder(OrderUpdateRequestModel(id=args.orderId,payment_type = payment_type)).observe(viewLifecycleOwner,
            Observer {
                it?.getValueOrNull().let {
                    it?.status.let {
                        it?.let {
                            if (it==1)
                            {
                                showToast("Payment method updated successfully")

                            }else
                            {
                                showToast("Somthing went wrong please try again")
                            }
                        }
                    }
                }
            })
    }


    fun updateOrderType(order_type:String)
    {
        homeViewModel.updateOrder(OrderUpdateRequestModel(id=args.orderId,order_type = order_type)).observe(viewLifecycleOwner,
            Observer {
                it?.getValueOrNull().let {
                    it?.status.let {
                        it?.let {
                            if (it==1)
                            {
                                showToast("Order type updated successfully")

                            }else
                            {
                                showToast("Somthing went wrong please try again")
                            }
                        }
                    }
                }
            })
    }

    override fun onDestroy() {
        Constants.SELECTED_ORDER = null
        Constants.USER_ID = null
        super.onDestroy()
    }
}