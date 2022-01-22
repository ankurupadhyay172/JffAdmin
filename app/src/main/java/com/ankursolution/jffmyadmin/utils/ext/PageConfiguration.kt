package com.ankursolution.jffmyadmin.utils.ext

import androidx.annotation.IdRes
import com.ankursolution.jffmyadmin.R

enum class PageConfiguration (
    val id:Int,
    val toolbarVisible:Boolean = true,
    val bottomNavigationBarVisible:Boolean = false,
    val isTopLeval:Boolean = false)
{
    SPLASH(
      R.id.splashScreenFragment,
        toolbarVisible = false
    ),LOGIN(
    R.id.loginFragment,
    toolbarVisible = false
    ),
    HOME(
        R.id.homeFragment,
        bottomNavigationBarVisible = true,
        isTopLeval = true,

    ),TAKEORDER(
    R.id.takeOrdersFragment,
    bottomNavigationBarVisible = true
    ),ADDITEM(
    R.id.showCategoriesFragment,
    bottomNavigationBarVisible = true
)
    ,SINGLEORDER(
    R.id.singleOrderFragment
    ),OTHER(0);

    companion object{
        fun getConfiguration(@IdRes id:Int):PageConfiguration{
            return values().firstOrNull{it.id==id}?:OTHER
        }
    }






}