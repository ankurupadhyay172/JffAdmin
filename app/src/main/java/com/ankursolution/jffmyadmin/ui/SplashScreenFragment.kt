package com.ankursolution.jffmyadmin.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.databinding.FragmentSplashScreenBinding

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment:BaseFragment<FragmentSplashScreenBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            navigationToMain()

        },2000)


    }

    private fun navigationToMain() {
        lifecycleScope.launchWhenResumed {
            val startFragmentId = when
            {
                sessionManager.getUserAuth()!=null->{
                    R.id.action_splashScreenFragment_to_homeFragment
                }else->{
                    R.id.action_splashScreenFragment_to_loginFragment
                }
            }
            findNavController().navigate(startFragmentId)
        }
    }


    override fun getLayoutId() = R.layout.fragment_splash_screen


    override fun getViewModel() = homeViewModel
}