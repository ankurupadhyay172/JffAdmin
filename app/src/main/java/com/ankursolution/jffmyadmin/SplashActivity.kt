package com.ankursolution.jffmyadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ankursolution.jffmyadmin.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    init {
        lifecycleScope.launchWhenResumed {
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        }
    }

}