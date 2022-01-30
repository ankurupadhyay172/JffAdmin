package com.ankursolution.jffmyadmin.data.model.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.OrderResultModel
import com.ankursolution.jffmyadmin.databinding.ItemPendingOrdersBinding
import com.ankursolution.jffmyadmin.ui.HomeFragmentDirections
import javax.inject.Inject

class AllPendingOrdersAdapter @Inject constructor():
    BaseListAdapter<OrderResultModel.Result, ItemPendingOrdersBinding>(DiffCallback()){
    var updateState:((pid:String?,status:String?)->Unit)? = null

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

            v?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToSingleOrderFragment(item?.id,title = item?.user_name))
        }

        binding.update.setOnClickListener {v->

            val types =v.context.resources.getStringArray(R.array.order_status)

            var adapter = OrderStatusAdapter(binding.mainItem.context,R.layout.order_status_item,
                types.toList())
            AlertDialog.Builder(v.context).setTitle("Update Status")
                .setAdapter(adapter) { dialog, which ->
                updateState?.invoke(item?.id,types[which])
                }.create().show()
        }


    }

}
