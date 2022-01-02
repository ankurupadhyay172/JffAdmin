package com.ankursolution.jffmyadmin.utils.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.data.model.AppError
import com.bumptech.glide.Glide


@BindingAdapter("isVisible")
fun View.showGone(show:Boolean){
    isVisible = show
}



@BindingAdapter("errorImage")
fun ImageView.erroRes(appError: AppError?){
    appError?.let {
        if(appError is AppError.ApiException.NoConnectivityException){
            setImageResource(R.drawable.img_antenna)
        }else
        {
            setImageResource(R.drawable.ic_error)
        }
    }
}

@BindingAdapter("errorTitle")
fun TextView.errorTitle(appError: AppError?){
    appError?.let {
        if(appError is AppError.ApiException.NoConnectivityException){
            setText("Oh no !")
        }else{
            setText("Somthing Went Wrong")
        }
    }
}



@BindingAdapter("errorText")
fun TextView.appError(appError: AppError?) {
    appError?.let {

        setText(appError.stringRes())
    }


    @BindingAdapter("errorText")
    fun ImageView.setImageUrl(url: String?) {
            url?.let {
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.slogo)
                    .into(this)
        }
    }
}



