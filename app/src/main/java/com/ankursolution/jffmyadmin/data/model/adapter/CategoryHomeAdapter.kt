package com.ankursolution.jffmyadmin.data.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.CategoryModel
import com.ankursolution.jffmyadmin.databinding.ItemCategoryBinding
import com.ankursolution.jffmyadmin.databinding.ItemCategoryDesignBinding
import com.ankursolution.jffmyadmin.ui.products.ShowCategoriesFragmentDirections
import com.ankursolution.jffmyadmin.ui.takeorders.TakeOrdersFragmentDirections
import javax.inject.Inject

class CategoryHomeAdapter @Inject constructor():
    BaseListAdapter<CategoryModel.Result, ItemCategoryDesignBinding>(DiffCallback()){

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

    override fun createBinding(parent: ViewGroup): ItemCategoryDesignBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category_design,parent,false)
    }

    override fun bind(binding: ItemCategoryDesignBinding, item: CategoryModel.Result?) {
        binding.model = item



        binding.headerlayout.setOnClickListener {v->
            v?.findNavController()?.navigate(TakeOrdersFragmentDirections.actionTakeOrdersFragmentToTakeOrdersProductsFragment(catId = item?.id))
        }


    }

}
