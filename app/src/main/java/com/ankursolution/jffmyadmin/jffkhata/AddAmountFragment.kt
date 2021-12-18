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
import com.ankursolution.jffmyadmin.data.model.AddTransactionRequestModel
import com.ankursolution.jffmyadmin.databinding.FragmentAddAmountBinding
import com.ankursolution.jffmyadmin.utils.ext.Common
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_amount.*

@AndroidEntryPoint
class AddAmountFragment : Fragment(R.layout.fragment_add_amount) {

    val args:AddAmountFragmentArgs by navArgs()
    val khataViewModel: KhataViewModel by viewModels()
    lateinit var bind:FragmentAddAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentAddAmountBinding.inflate(inflater,container,false)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTransaction()
        args.amount?.let {
            bind.etInput.setText(it)  
        }



    }

    private fun addTransaction() {
        bind.btnSave.setOnClickListener {
            if (bind.etInput.text.isNotEmpty())
            {
                bind.progressBar.visibility  = View.VISIBLE
                bind.btnSave.visibility = View.GONE
                var amount = if(args.isgave){
                    AddTransactionRequestModel(args.userid,bind.etInput.text.toString(),null)
                }else{
                    AddTransactionRequestModel(args.userid,null,bind.etInput.text.toString())
                }

                khataViewModel.addKhataTransaction(amount).observe(viewLifecycleOwner, Observer {
                    it?.getValueOrNull()?.let {
                        if (it.status==1)
                        {
                            Toast.makeText(requireContext(), "Transaction Added Successfully", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }else{
                            bind.progressBar.visibility  = View.GONE
                            bind.btnSave.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Somthing went wrong plese try again", Toast.LENGTH_SHORT).show()
                        }

                    }


                })
            }else{
                bind.etInput.setError("Please enter some amount")
            }


        }


    }


}