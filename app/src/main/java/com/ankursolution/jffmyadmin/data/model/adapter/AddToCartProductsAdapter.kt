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

class AddToCartProductsAdapter @Inject constructor():
    BaseListAdapter<ProductModel.Result, ItemAddToCartProductsBinding>(DiffCallback()){

    var addToCartClick:((pid:String?,vid:String?,quan:Int)->Unit)? = null
    var updateCart:((pid:String?,vid:String?,quan:Int)->Unit)? = null
    var addToProduct:((pid:String?,product_name:String?,product_price:String?,product_image:String?,product_size:String?,quan:Int)->Unit)?=null
    class DiffCallback:DiffUtil.ItemCallback<ProductModel.Result>(){
        override fun areItemsTheSame(
            oldItem: ProductModel.Result,
            newItem: ProductModel.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: ProductModel.Result,
            newItem: ProductModel.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemAddToCartProductsBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_add_to_cart_products,parent,false)
    }

    override fun bind(binding: ItemAddToCartProductsBinding, item: ProductModel.Result?) {

        var counter =0;
        binding.model = item
        item?.varients.let {

            if (it.isNullOrEmpty().not())
            {
                binding.varient = it?.get(0)
                binding.size.setOnClickListener {v->

                    var adapter = SpinnerAdapter(binding.price.context,R.layout.spinner_item,it!!)
                    AlertDialog.Builder(v.context).setTitle("Select Size")
                        .setAdapter(adapter) { dialog, which ->
                            counter =0
                            binding.isCartVisible = false
                            binding.varient = it.get(which)
                        }.create().show()
                }
            }
        }

        binding.addToCart.setOnClickListener {
            //addToCartClick?.invoke()
            counter++
            binding.counter.setText(counter.toString())
            binding.isCartVisible = true


            addToCartClick?.invoke(item?.id,binding.varient?.id,counter)
        }
        binding.plus.setOnClickListener {
            counter++
            binding.counter.setText(counter.toString())
            updateCart?.invoke(item?.id,binding.varient?.id,counter)
        }
        binding.minus.setOnClickListener {
            counter--
            if (counter<=0)
            {
                counter =0
                binding.isCartVisible = false
            }
            binding.counter.setText(counter.toString())
            updateCart?.invoke(item?.id,binding.varient?.id,counter)
        }


//        binding.headerlayout.setOnClickListener {v->
//            v?.findNavController()?.navigate(ShowCategoriesFragmentDirections.actionAddCategoriesFragmentToShowProductsFragment(item?.id))
//
//
//        }


    }

}
