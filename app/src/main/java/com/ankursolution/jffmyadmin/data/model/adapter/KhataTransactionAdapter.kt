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
import com.ankursolution.jffmyadmin.data.model.JffKhataTransactionModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.databinding.ItemKhataDetailsBinding
import com.ankursolution.jffmyadmin.databinding.ItemKhatausersBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataHomeFragmentDirections
import javax.inject.Inject

class KhataTransactionAdapter @Inject constructor():BaseListAdapter<JffKhataTransactionModel.Result,ItemKhataDetailsBinding>(DiffCallback()){

    class DiffCallback:DiffUtil.ItemCallback<JffKhataTransactionModel.Result>(){
        override fun areItemsTheSame(
            oldItem: JffKhataTransactionModel.Result,
            newItem: JffKhataTransactionModel.Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: JffKhataTransactionModel.Result,
            newItem: JffKhataTransactionModel.Result
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemKhataDetailsBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_khata_details,parent,false)
    }

    override fun bind(binding: ItemKhataDetailsBinding, item: JffKhataTransactionModel.Result?) {

        binding.model = item

        if (item?.give?.isNotEmpty() == false)
        {
            binding.gave.visibility = View.GONE
        }
        if (item?.got?.isNotEmpty() == false)
        {
            binding.get.visibility = View.GONE
        }
        if (item?.got.isNullOrEmpty().not())
        {
            binding.textView2.text = "Bal \u20B9"+item?.got
        }

        if (item?.give.isNullOrEmpty().not())
        {
            binding.textView2.text = "Bal \u20B9"+item?.give
        }
    }

}
