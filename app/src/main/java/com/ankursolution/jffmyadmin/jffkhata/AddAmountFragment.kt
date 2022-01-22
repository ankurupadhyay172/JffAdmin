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
import com.ankursolution.jffmyadmin.data.model.UpdateAmountRequestModel
import com.ankursolution.jffmyadmin.databinding.FragmentAddAmountBinding
import dagger.hilt.android.AndroidEntryPoint

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

        val isGave = args.isgave
        val uid = args.id
        addTransaction(isGave,uid)
        args.amount?.let {
            if (it!="na")
            bind.etInput.setText(it)
        }


    }

    private fun addTransaction(isGave: Boolean, uid: String) {
        bind.btnSave.setOnClickListener {
            if (bind.etInput.text.isNotEmpty())
            {
                bind.progressBar.visibility  = View.VISIBLE
                bind.btnSave.visibility = View.GONE
                var amount = if(isGave){
                    AddTransactionRequestModel(uid,bind.etInput.text.toString(),null)
                }else{
                    AddTransactionRequestModel(uid,null,bind.etInput.text.toString())
                }


                if (args.type=="na")
                {
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

                    Toast.makeText(requireContext(), ""+args.method, Toast.LENGTH_SHORT).show()
                    khataViewModel.updateAmount(UpdateAmountRequestModel(uid,amount = bind.etInput.text.toString(),type = args.method,uid =args.userId )).observe(viewLifecycleOwner,
                        Observer {
                            it?.getValueOrNull()?.let {
                                if (it.status==1)
                                {
                                    Toast.makeText(requireContext(), "Transaction Updated Successfully", Toast.LENGTH_SHORT).show()
                                    findNavController().popBackStack()
                                }else{
                                    bind.progressBar.visibility  = View.GONE
                                    bind.btnSave.visibility = View.VISIBLE
                                    Toast.makeText(requireContext(), "Somthing went wrong plese try again", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                }

            }else{
                bind.etInput.setError("Please enter some amount")
            }
        }


    }


}