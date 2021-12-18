package com.ankursolution.jffmyadmin.jffkhata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.data.model.KhataTransactionRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.AllKhataUsersAdapter
import com.ankursolution.jffmyadmin.data.model.adapter.KhataTransactionAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentKhataHomeBinding
import com.ankursolution.jffmyadmin.databinding.FragmentKhataUserTransactionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_khata_home.*
import javax.inject.Inject

@AndroidEntryPoint
class KhataUserTransactionFragment : Fragment(R.layout.fragment_khata_user_transaction) {

    val khataViewModel: KhataViewModel by viewModels()
    lateinit var binding:FragmentKhataUserTransactionBinding

    val args:KhataUserTransactionFragmentArgs by navArgs()

    @Inject
    lateinit var mAdapter:KhataTransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKhataUserTransactionBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getUsers()
        rvTransaction.adapter = mAdapter

//        binding.addCustomer.setOnClickListener {
//            findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToAddKhataCustomerFragment())
//        }

        binding.gave.setOnClickListener {
            findNavController()?.navigate(KhataUserTransactionFragmentDirections.actionKhataUserTransactionFragmentToAddAmountFragment(isgave = true,userid = args.userid.toString()))
        }


        binding.get.setOnClickListener {
            findNavController()?.navigate(KhataUserTransactionFragmentDirections.actionKhataUserTransactionFragmentToAddAmountFragment(isgave = false,userid = args.userid.toString()))
        }
    }

    private fun getUsers() {
        khataViewModel.getKhataTransaction(KhataTransactionRequestModel(args.userid)).observe(requireActivity(), Observer { loadState->
            loadState.getValueOrNull()?.let {
            mAdapter.submitList(it.result)
            }
        })
    }




}