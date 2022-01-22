package com.ankursolution.jffmyadmin.jffkhata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.data.model.adapter.AllKhataUsersAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentKhataHomeBinding
import com.ankursolution.jffmyadmin.retrofit.LoadState
import com.ankursolution.jffmyadmin.utils.ext.NoConnectivityException
import com.ankursolution.jffmyadmin.utils.ext.checkConnect
import com.ankursolution.jffmyadmin.utils.ext.toAppError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class KhataHomeFragment : Fragment(R.layout.fragment_khata_home) {

    val khataViewModel: KhataViewModel by viewModels()
    lateinit var binding:FragmentKhataHomeBinding

    @Inject
    lateinit var mAdapter:AllKhataUsersAdapter

    var mResult:MutableLiveData<List<JffKhataUserModel.Result>> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUsers()


    }



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

        binding.isLoading = true
        binding.isVisible = true



        getUserData()
        deleteUser()
        editUser()
        binding.rvTransaction.adapter = mAdapter
        binding.addCustomer.setOnClickListener {
            findNavController().navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToAddKhataCustomerFragment())
        }




        checkInternet()

    }

    private fun editUser() {
        mAdapter.updateUser ={_,data->
            findNavController()?.navigate(KhataHomeFragmentDirections.actionKhataHomeFragmentToAddKhataCustomerFragment(data))
        }
    }

    private fun getUserData() {
        mResult.observe(viewLifecycleOwner, Observer {

            mAdapter.submitList(it)
            mAdapter.notifyDataSetChanged()
            binding.isLoading = false
        })
    }

    private fun deleteUser() {
        mAdapter.deleteUser = {
            khataViewModel.deleteKhataUser(CommonRequestModel(it)).observe(viewLifecycleOwner,
                {
                    it.getValueOrNull().let {
                        if (it?.status==1)
                        {
                            Toast.makeText(requireContext(), "User Deleted successfully", Toast.LENGTH_SHORT).show()
                            getUsers()
                        }
                    }



                })
        }


    }

    private fun checkInternet() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            requireContext().checkConnect().collect {
            when(it)
            {

                false->{
                    Toast.makeText(requireContext(), "Connect with internet", Toast.LENGTH_SHORT).show()
                    binding.isVisible = true
                    binding.isLoading = false
                    binding.error = NoConnectivityException().toAppError()
                }
                true->{
                    binding.isVisible = false
                    getUsers()
                }

            }
            }
        }



    }

    private fun getUsers() {
        khataViewModel.getAllJffKhataUsers().observe(requireActivity(), Observer {loadState->
            loadState.getValueOrNull()?.let {
                mResult.postValue(it.result)
            } })
    } }