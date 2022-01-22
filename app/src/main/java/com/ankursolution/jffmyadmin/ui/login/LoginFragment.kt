package com.ankursolution.jffmyadmin.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.LoginRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.databinding.FragmentLoginBinding
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment :BaseFragment<FragmentLoginBinding,HomeViewModel>(){

    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login.setOnClickListener {
            if (mobile_no.text.toString().isEmpty())
            {
            return@setOnClickListener
            }else{
                li_pass.visibility = View.VISIBLE
                login.visibility = View.GONE
            }

        }
        submit_pass.setOnClickListener {
            if (pass.text.toString().isEmpty())
                return@setOnClickListener
            else{
                progressbar2.visibility = View.VISIBLE
                submit_pass.visibility = View.GONE
                getLoginData()
            }
        }



    }

    private fun getLoginData() {
        homeViewModel.loginUser(LoginRequestModel(mobile_no.text.toString(),pass.text.toString())).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {

                    showToast("successfully login")
                    sessionManager.saveUserAuth(it.result[0])
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

                }else{
                    progressbar2.visibility = View.GONE
                    submit_pass.visibility = View.VISIBLE
                    showToast("please check userid and password")
                }
            }
        })
    }


    override fun getLayoutId() = R.layout.fragment_login


    override fun getViewModel() = homeViewModel
}