package com.ankursolution.jffmyadmin.data.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.base.BaseRecyclerAdapter
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.data.model.OrderResultModel
import com.ankursolution.jffmyadmin.databinding.ItemKhatausersBinding
import com.ankursolution.jffmyadmin.databinding.ItemPendingOrdersBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataHomeFragmentDirections
import com.ankursolution.jffmyadmin.ui.HomeFragment
import com.ankursolution.jffmyadmin.ui.HomeFragmentDirections
import javax.inject.Inject

class AllPendingOrdersAdapter @Inject constructor():BaseListAdapter<OrderResultModel.Result,ItemPendingOrdersBinding>(DiffCallback()){

    class DiffCallback:DiffUtil.ItemCallback<OrderResultModel.Result>(){
        override fun areItemsTheSame(
            oldItem: OrderResultModel.Result,
            newItem: OrderResultModel.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OrderResultModel.Result,
            newItem: OrderResultModel.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemPendingOrdersBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pending_orders,parent,false)
    }

    override fun bind(binding: ItemPendingOrdersBinding, item: OrderResultModel.Result?) {
        binding.model = item
        binding.mainItem.setOnClickListener {v->
           // it?.findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToKhataUserTransactionFragment(item?.id))

            v?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToSingleOrderFragment(item?.id))
        }


    }

}
