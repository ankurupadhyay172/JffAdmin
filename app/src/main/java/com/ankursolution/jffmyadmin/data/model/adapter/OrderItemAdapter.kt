package com.ankursolution.jffmyadmin.data.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.SingleOrderResult
import com.ankursolution.jffmyadmin.databinding.ItemOrdersBinding
import javax.inject.Inject

class OrderItemAdapter @Inject constructor():
    BaseListAdapter<SingleOrderResult.Items, ItemOrdersBinding>(DiffCallback()){

    class DiffCallback:DiffUtil.ItemCallback<SingleOrderResult.Items>(){
        override fun areItemsTheSame(
            oldItem: SingleOrderResult.Items,
            newItem: SingleOrderResult.Items
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SingleOrderResult.Items,
            newItem: SingleOrderResult.Items
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemOrdersBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_orders,parent,false)
    }

    override fun bind(binding: ItemOrdersBinding, item: SingleOrderResult.Items?) {
        binding.model = item
//        binding.mainItem.setOnClickListener {v->
//           // it?.findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToKhataUserTransactionFragment(item?.id))
//
//            v?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToSingleOrderFragment(item?.id))
//        }


    }

}
