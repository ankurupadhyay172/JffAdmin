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
import com.ankursolution.jffmyadmin.data.model.*
import com.ankursolution.jffmyadmin.databinding.*
import com.ankursolution.jffmyadmin.jffkhata.KhataHomeFragmentDirections
import com.ankursolution.jffmyadmin.ui.HomeFragment
import com.ankursolution.jffmyadmin.ui.HomeFragmentDirections
import com.ankursolution.jffmyadmin.ui.products.ShowCategoriesFragmentDirections
import com.ankursolution.jffmyadmin.ui.products.ShowProductsFragmentArgs
import com.bumptech.glide.Glide
import javax.inject.Inject

class ProductsAdapter @Inject constructor():BaseListAdapter<ProductModel.Result,ItemProductBinding>(DiffCallback()){

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
//        item?.varients.let {
//
//            if (it.isNullOrEmpty().not())
//            binding.varient = it?.get(0)
//        }


//        binding.headerlayout.setOnClickListener {v->
//            v?.findNavController()?.navigate(ShowCategoriesFragmentDirections.actionAddCategoriesFragmentToShowProductsFragment(item?.id))
//
//
//        }


    }

}
