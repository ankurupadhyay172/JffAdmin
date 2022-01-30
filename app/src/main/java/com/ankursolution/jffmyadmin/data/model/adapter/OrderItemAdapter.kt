package com.ankursolution.jffmyadmin.data.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.SingleOrderResult
import com.ankursolution.jffmyadmin.databinding.ItemOrdersBinding
import com.ankursolution.jffmyadmin.ui.SingleProductFragment
import com.ankursolution.jffmyadmin.ui.orders.SingleOrderFragmentDirections
import javax.inject.Inject

class OrderItemAdapter @Inject constructor():
    BaseListAdapter<SingleOrderResult.Items, ItemOrdersBinding>(DiffCallback()){
    var deleteItem:((id:String?)->Unit)? = null
    var updateCart:((id:String?,quan:Int)->Unit)? = null


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
        var counter = item?.quan?.toInt()?:1

//        binding.itemView.setOnClickListener {
//            it?.findNavController()?.navigate(SingleOrderFragmentDirections.actionSingleOrderFragmentToSingleProductFragment(item?.pid,varient = item?.product_size))
//        }

        binding.imgPlus.setOnClickListener {
            counter++
            updateCart?.invoke(item?.id,counter)
        }

        binding.imgMinus.setOnClickListener {
            if (counter>1)
            {
                counter--
                updateCart?.invoke(item?.id,counter)
                binding.tvNumber.setText(counter.toString())
            }else{
                deleteItem?.invoke(item?.id)
            }

        }

//        binding.mainItem.setOnClickListener {v->
//           // it?.findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToKhataUserTransactionFragment(item?.id))
//
//            v?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToSingleOrderFragment(item?.id))
//        }


    }

}
