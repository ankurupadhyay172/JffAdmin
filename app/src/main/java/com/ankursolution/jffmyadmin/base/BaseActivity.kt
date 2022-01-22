package com.ankursolution.jffmyadmin.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.ankursolution.jffmyadmin.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity:AppCompatActivity() {
    private var progressDialog :ProgressDialog? = null

    fun showMessage(stringRes:Int)
    {
        val snackbar  = Snackbar.make(findViewById(R.id.container),stringRes,Snackbar.LENGTH_LONG)
        snackbar.anchorView = findViewById(R.id.container)
        snackbar.show()
    }

}