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
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.databinding.FragmentEntryDetailBinding
import com.ankursolution.jffmyadmin.utils.ext.Common
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryDetailFragment : Fragment(R.layout.fragment_entry_detail) {

    val args:EntryDetailFragmentArgs by navArgs()
    lateinit var binding:FragmentEntryDetailBinding
    val khataViewModel: KhataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEntryDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.transactionId.toString()

        binding.tvDelete.setOnClickListener {

            binding.tvDelete.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE

            khataViewModel.deleteSingleKhataTransaction(CommonRequestModel(id)).observe(viewLifecycleOwner,
                Observer {
                    it?.getValueOrNull().let {
                        if (it?.status==1)
                        {
                           findNavController()?.popBackStack()
                        }
                        else
                        {
                            Toast.makeText(requireContext(), "Transaction Deleted successfully", Toast.LENGTH_SHORT).show()
                            binding.tvDelete.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE

                        }
                    }
                })
        }



        khataViewModel.getSingleKhataTransaction(CommonRequestModel(args.transactionId.toString())).observe(viewLifecycleOwner,
            Observer {
                it?.getValueOrNull().let {
                    it?.let {

                        binding.model = it.result[0]
                        var isGive = false
                        var method = "na"
                        var amount = if (it.result[0].give.isNullOrEmpty().not())
                        {
                            method = "give"
                            isGive = true
                            it.result[0].give
                        }
                        else
                        {
                            method = "got"
                            isGive = false
                            it.result[0].got
                        }

                        binding.type.setText("You "+method.toUpperCase())
                        binding.payment.setText(Common.setPrice(amount))
                        binding.tvEdit.setOnClickListener {v->
                            findNavController().navigate(EntryDetailFragmentDirections.actionEntryDetailFragmentToAddAmountFragment(amount = amount,isGive,id =it.result[0].id,type = "update",method = method,userId = it.result[0].user_id))
                        }
                    }
                }
            })

    }

}