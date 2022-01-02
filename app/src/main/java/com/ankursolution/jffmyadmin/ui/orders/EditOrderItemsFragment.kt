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
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.data.model.emumeration.EditType
import com.ankursolution.jffmyadmin.databinding.FragmentorderitemfragmentBinding
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_order.*

@AndroidEntryPoint
class EditOrderItemsFragment:BaseFragment<FragmentorderitemfragmentBinding, HomeViewModel>()  {
    val homeViewModel: HomeViewModel by viewModels()
    val args: EditOrderItemsFragmentArgs by navArgs()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().submit.setOnClickListener {
            updateAddress()
        }
            if (args.address.equals("na").not())
            {
                getViewDataBinding().tvTitle.hint = "Enter Address"
                getViewDataBinding().tvTitle.text = resources.getString(R.string.address)
                getViewDataBinding().edtItem.setText(args.address)
            }


        if (args.instruction.equals("na").not())
        {
            getViewDataBinding().tvTitle.hint = "Enter User Instruction"
            getViewDataBinding().tvTitle.text = resources.getString(R.string.user_instruction)
            getViewDataBinding().edtItem.setText(args.instruction)
        }

        if (args.tableno.equals("na").not())
        {
            getViewDataBinding().tvTitle.hint = "Enter Table No."
            getViewDataBinding().tvTitle.text = resources.getString(R.string.table_no)
            getViewDataBinding().edtItem.setText(args.tableno)
        }

        if (args.discount.equals("na").not())
        {
            getViewDataBinding().tvTitle.hint = "Enter Discount"
            getViewDataBinding().tvTitle.text = resources.getString(R.string.discount)
            getViewDataBinding().edtItem.setText(args.discount)
        }
    }


    private fun updateAddress() {
        homeViewModel.updateOrder(getSelected()).observe(viewLifecycleOwner,
            Observer {
                it?.getValueOrNull().let {
                    it?.status.let {
                        it?.let {
                            if (it==1)
                            {
                                showToast("Address updated successfully")
                                findNavController().popBackStack()
                            }else
                            {
                                showToast("Somthing went wrong please try again")
                            }
                        }
                    }
                }
            })
    }

    fun getSelected():OrderUpdateRequestModel
    {
        return when(args.type)
        {
            resources.getString(R.string.address)->{OrderUpdateRequestModel(id = args.orderId,address = getViewDataBinding().edtItem.text.toString())}
            resources.getString(R.string.user_instruction)->{OrderUpdateRequestModel(id = args.orderId,instruction = getViewDataBinding().edtItem.text.toString())}
            resources.getString(R.string.table_no)->{OrderUpdateRequestModel(id = args.orderId,table_no = getViewDataBinding().edtItem.text.toString())}
            resources.getString(R.string.discount)->{OrderUpdateRequestModel(id = args.orderId,discount = getViewDataBinding().edtItem.text.toString())}
            else->{OrderUpdateRequestModel(id = args.orderId)}
        }
    }


    override fun getLayoutId() = R.layout.fragmentorderitemfragment


    override fun getViewModel() = homeViewModel

}