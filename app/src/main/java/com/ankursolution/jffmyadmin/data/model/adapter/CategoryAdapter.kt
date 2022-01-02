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
import com.ankursolution.jffmyadmin.data.model.CategoryModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.data.model.OrderResultModel
import com.ankursolution.jffmyadmin.data.model.SingleOrderResult
import com.ankursolution.jffmyadmin.databinding.ItemCategoryBinding
import com.ankursolution.jffmyadmin.databinding.ItemKhatausersBinding
import com.ankursolution.jffmyadmin.databinding.ItemOrdersBinding
import com.ankursolution.jffmyadmin.databinding.ItemPendingOrdersBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataHomeFragmentDirections
import com.ankursolution.jffmyadmin.ui.HomeFragment
import com.ankursolution.jffmyadmin.ui.HomeFragmentDirections
import com.ankursolution.jffmyadmin.ui.products.ShowCategoriesFragmentDirections
import com.ankursolution.jffmyadmin.ui.products.ShowProductsFragmentArgs
import com.bumptech.glide.Glide
import javax.inject.Inject

class CategoryAdapter @Inject constructor():BaseListAdapter<CategoryModel.Result,ItemCategoryBinding>(DiffCallback()){

    class DiffCallback:DiffUtil.ItemCallback<CategoryModel.Result>(){
        override fun areItemsTheSame(
            oldItem: CategoryModel.Result,
            newItem: CategoryModel.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CategoryModel.Result,
            newItem: CategoryModel.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemCategoryBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category,parent,false)
    }

    override fun bind(binding: ItemCategoryBinding, item: CategoryModel.Result?) {
        binding.model = item



        binding.headerlayout.setOnClickListener {v->
            v?.findNavController()?.navigate(ShowCategoriesFragmentDirections.actionShowCategoriesFragmentToShowProductsFragment(catId = item?.id))


        }


    }

}
