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

class ProductsAdapter @Inject constructor():
    BaseListAdapter<ProductModel.Result, ItemProductBinding>(DiffCallback()){

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

    override fun createBinding(parent: ViewGroup): ItemProductBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,parent,false)
    }

    override fun bind(binding: ItemProductBinding, item: ProductModel.Result?) {

        binding.model = item
        item?.varients.let {

            if (it.isNullOrEmpty().not())
            {
                binding.varient = it?.get(0)
                binding.size.setOnClickListener {v->

                    var adapter = SpinnerAdapter(binding.price.context,R.layout.spinner_item,it!!)
                    AlertDialog.Builder(v.context).setTitle("Select Size")
                        .setAdapter(adapter) { dialog, which ->
                            binding.varient = it.get(which)
                        }.create().show()
                }
            }
        }


//        binding.headerlayout.setOnClickListener {v->
//            v?.findNavController()?.navigate(ShowCategoriesFragmentDirections.actionAddCategoriesFragmentToShowProductsFragment(item?.id))
//
//
//        }


    }

}
