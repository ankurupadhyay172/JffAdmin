package com.ankursolution.jffmyadmin.data.model.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseListAdapter
import com.ankursolution.jffmyadmin.data.model.CategoryModel
import com.ankursolution.jffmyadmin.data.model.Varients
import com.ankursolution.jffmyadmin.databinding.ItemCategoryBinding
import com.ankursolution.jffmyadmin.databinding.ItemProductVarientBinding
import com.ankursolution.jffmyadmin.ui.products.ShowCategoriesFragmentDirections
import com.google.gson.Gson
import javax.inject.Inject

class ProductVarientAdapter @Inject constructor():
    BaseListAdapter<Varients, ItemProductVarientBinding>(DiffCallback()){

    var delete:((id:String?,url:String?)->Unit)?=null
    var update:((model:String?)->Unit)?=null
    class DiffCallback:DiffUtil.ItemCallback<Varients>(){
        override fun areItemsTheSame(
            oldItem: Varients,
            newItem: Varients
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Varients,
            newItem: Varients
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun createBinding(parent: ViewGroup): ItemProductVarientBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product_varient,parent,false)
    }

    override fun bind(binding: ItemProductVarientBinding, item: Varients?) {
        binding.model = item


        binding.threeDot.setOnClickListener {v->
            val types =v.context.resources.getStringArray(R.array.choose_action)


            var adapter = OrderStatusAdapter(v.context,R.layout.order_status_item,
                types.toList())
            AlertDialog.Builder(v.context).setTitle("Choose Action")
                .setAdapter(adapter) { dialog, which ->
                    when(which)
                    {
                        0->{update?.invoke(Gson().toJson(item))}
                        1->{delete?.invoke(item?.id,item?.image)}
                    }
                }.create().show()
        }



    }

}
