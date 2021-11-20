package com.ankursolution.jffmyadmin.data.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseRecyclerAdapter
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.databinding.ItemKhatausersBinding

class AllKhataUsersAdapter(val context:Context,val list: List<JffKhataUserModel.Result>):RecyclerView.Adapter<AllKhataUsersAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: ItemKhatausersBinding) :RecyclerView.ViewHolder(itemView.root){
        val binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = ItemKhatausersBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.model = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
