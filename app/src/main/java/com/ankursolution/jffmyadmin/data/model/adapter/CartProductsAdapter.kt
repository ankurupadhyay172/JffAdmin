package com.ankursolution.jffmyadmin.data.model.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.*
import com.ankursolution.jffmyadmin.databinding.*
import javax.inject.Inject

class CartProductsAdapter @Inject constructor():
    BaseListAdapter<CartOrderResult.Result, ItemCartBinding>(DiffCallback()){

    var deleteCart:((id:String?)->Unit)? = null
    var updateCart:((id:String?,quan:Int)->Unit)? = null

    class DiffCallback:DiffUtil.ItemCallback<CartOrderResult.Result>(){
        override fun areItemsTheSame(
            oldItem: CartOrderResult.Result,
            newItem: CartOrderResult.Result
        ): Boolean {
            return oldItem.p_id == newItem.cart_id
        }
        override fun areContentsTheSame(
            oldItem: CartOrderResult.Result,
            newItem: CartOrderResult.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemCartBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cart,parent,false)
    }

    override fun bind(binding: ItemCartBinding, item: CartOrderResult.Result?) {



        binding.model = item
        var counter = item?.quan?.toInt()?:1
        binding.deleteItem.setOnClickListener {
            deleteCart?.invoke(item?.cart_id)
        }
        binding.imgPlus.setOnClickListener {
            counter++
            updateCart?.invoke(item?.cart_id,counter)
            binding.tvNumber.setText(counter.toString())
        }

        binding.imgMinus.setOnClickListener {
            if (counter>1)
            {
                counter--
                updateCart?.invoke(item?.cart_id,counter)
                binding.tvNumber.setText(counter.toString())
            }

        }


    }

}
