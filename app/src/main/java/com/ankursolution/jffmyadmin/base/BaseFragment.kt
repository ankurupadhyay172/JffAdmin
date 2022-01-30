package com.ankursolution.jffmyadmin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.ankursolution.jffmyadmin.utils.ext.autoCleared

abstract class BaseFragment<T:ViewDataBinding,V:BaseViewModel>: Fragment() {

    private var mViewDataBinding by autoCleared<T>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<T>(inflater,getLayoutId(),container,false).also {
        mViewDataBinding = it
   //     mViewDataBinding.setVariable(getBindingVariable(),getViewModel())
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewDataBinding.executePendingBindings()
    }.root
    /*
    @return layout resource id
    */
    @LayoutRes
    abstract fun getLayoutId():Int

    /*set binding variable
    @return veriable id */

//    abstract fun getBindingVariable():Int

    /*
    Override for set binding variable
    @return variable id
    */

    abstract fun getViewModel():V
/*
    Method for get View data binding
    */

    fun getViewDataBinding():T{
        return mViewDataBinding
    }

    fun showToast(message:String?){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }



}