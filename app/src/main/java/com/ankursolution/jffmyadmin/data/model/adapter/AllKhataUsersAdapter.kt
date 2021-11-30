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
import com.ankursolution.jffmyadmin.databinding.ItemKhatausersBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataHomeFragmentDirections
import javax.inject.Inject

class AllKhataUsersAdapter @Inject constructor():BaseListAdapter<JffKhataUserModel.Result,ItemKhatausersBinding>(DiffCallback()){

    class DiffCallback:DiffUtil.ItemCallback<JffKhataUserModel.Result>(){
        override fun areItemsTheSame(
            oldItem: JffKhataUserModel.Result,
            newItem: JffKhataUserModel.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: JffKhataUserModel.Result,
            newItem: JffKhataUserModel.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemKhatausersBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_khatausers,parent,false)
    }

    override fun bind(binding: ItemKhatausersBinding, item: JffKhataUserModel.Result?) {
        binding.model = item
        binding.mainItem.setOnClickListener {
            it?.findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToKhataUserTransactionFragment(item?.id))

        }


    }

}
