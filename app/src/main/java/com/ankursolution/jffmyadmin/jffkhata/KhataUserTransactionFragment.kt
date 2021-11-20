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
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.data.model.adapter.AllKhataUsersAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentKhataHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KhataUserTransactionFragment : Fragment(R.layout.fragment_khata_home) {

    val khataViewModel: KhataViewModel by viewModels()
    lateinit var binding:FragmentKhataHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKhataHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getUsers()

        binding.addCustomer.setOnClickListener {
            findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToAddKhataCustomerFragment())
        }

    }

    private fun getUsers() {
        khataViewModel.getAllJffKhataUsers().observe(requireActivity(), Observer {loadState->
            loadState.getValueOrNull()?.let {

                val adapter = AllKhataUsersAdapter(requireContext(),it.result)
                binding.rvUsers.adapter = adapter


            }


        })
    }

}