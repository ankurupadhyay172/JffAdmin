package com.ankursolution.jffmyadmin.utils.ext.bindings

import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ankursolution.jffmyadmin.R
import com.bumptech.glide.Glide

@BindingAdapter("setdelivery")
fun RadioButton.setDelivery(text:String?){
    if (text==resources.getString(R.string.delivery))
    {
        this.isChecked = true
    }
}

@BindingAdapter("settable")
fun RadioButton.setTable(text:String?){
    if (text==resources.getString(R.string.table))
    {
        this.isChecked = true
    }
}

@BindingAdapter("settakeaway")
fun RadioButton.setTakeAway(text:String?){
    if (text==resources.getString(R.string.takeaway))
    {
        this.isChecked = true
    }
}

@BindingAdapter("setonline")
fun RadioButton.setOnline(text:String?){
    if (text==resources.getString(R.string.online))
    {
        this.isChecked = true
    }
}


@BindingAdapter("setcod")
fun RadioButton.setCod(text:String?){
    if (text==resources.getString(R.string.cod))
    {
        this.isChecked = true
    }
}


@BindingAdapter("setpad")
fun RadioButton.setPad(text:String?){
    if (text==resources.getString(R.string.pad))
    {
        this.isChecked = true
    }
}

@BindingAdapter("setprice")
fun TextView.setPrice(text: String?){
    if (text.isNullOrEmpty().not())
    this.setText("₹ $text")
}

@BindingAdapter("setImage")
fun ImageView.setImageData(url:String?)
{
    url?.let {
        Glide.with(this).load(it).placeholder(R.drawable.slogo).into(this)
    }
}