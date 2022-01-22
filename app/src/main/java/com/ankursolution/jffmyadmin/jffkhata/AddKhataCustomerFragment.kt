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
import com.ankursolution.jffmyadmin.data.model.AddUserRequestModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.databinding.FragmentAddKhataCustomerBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_khata_customer.*
import kotlinx.android.synthetic.main.fragment_add_khata_customer.view.*

@AndroidEntryPoint
class AddKhataCustomerFragment : Fragment(R.layout.fragment_add_khata_customer) {

    lateinit var bind:FragmentAddKhataCustomerBinding
    val khataViewModel: KhataViewModel by viewModels()
    val args:AddKhataCustomerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentAddKhataCustomerBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUserData()


        bind.submit.setOnClickListener {


            if (args.id!="na")
            {
                updateUser()
            }else
            addJffUser()


        }





    }

    private fun updateUser() {
        try {
            if (bind.name.text.isNotEmpty())
            {
                bind.progress.visibility = View.VISIBLE
                bind.submit.visibility = View.GONE


                khataViewModel.updateKhataUser(AddUserRequestModel(mobile_no = bind.mobileNo.text.toString(),user_name = bind.name.text.toString(),address = address.text.toString(),user_id = bind.model?.id)).observe(requireActivity(),
                    Observer { loadState->
                        loadState?.getValueOrNull().let {

                            if (it?.status==1)
                            {
                                Toast.makeText(requireContext(), "Customer added successfully", Toast.LENGTH_SHORT).show()
                                findNavController()?.popBackStack()
                            }
                            else{

                                bind.progress.visibility = View.GONE
                                bind.submit.visibility = View.VISIBLE

                            }
                        }

                    })
            }
            else{
                Toast.makeText(requireContext(), "Please Enter Customer Name", Toast.LENGTH_SHORT).show()
            }

        }catch (e:Exception){
            Toast.makeText(requireContext(), ""+e.message, Toast.LENGTH_SHORT).show()}
        bind.progress.visibility = View.GONE
        bind.submit.visibility = View.VISIBLE

    }

    private fun addJffUser() {
        try {
            if (bind.name.text.isNotEmpty())
            {
                bind.progress.visibility = View.VISIBLE
                bind.submit.visibility = View.GONE


                khataViewModel.addKhataUser(AddUserRequestModel(bind.mobileNo.text.toString(),bind.name.text.toString(),address.text.toString())).observe(requireActivity(),
                    Observer { loadState->
                        loadState?.getValueOrNull().let {

                            if (it?.status==1)
                            {
                                Toast.makeText(requireContext(), "Customer added successfully", Toast.LENGTH_SHORT).show()
                                findNavController()?.popBackStack()
                            }
                            else{

                                bind.progress.visibility = View.GONE
                                bind.submit.visibility = View.VISIBLE

                            }
                        }

                    })
            }
            else{
                Toast.makeText(requireContext(), "Please Enter Customer Name", Toast.LENGTH_SHORT).show()
            }

        }catch (e:Exception){
            Toast.makeText(requireContext(), ""+e.message, Toast.LENGTH_SHORT).show()}
        bind.progress.visibility = View.GONE
        bind.submit.visibility = View.VISIBLE

    }

    private fun setUserData() {
        if (args.id!="na")
        {
            var data =Gson().fromJson(args.id,JffKhataUserModel.Result::class.java)
            bind.model = data
        }
    }
}


