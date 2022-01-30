package com.ankursolution.jffmyadmin.ui.products

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankursolution.jffmyadmin.BR
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.ProductRequestModel
import com.ankursolution.jffmyadmin.data.model.ProductVarientRequestModel
import com.ankursolution.jffmyadmin.data.model.Varients
import com.ankursolution.jffmyadmin.data.model.adapter.ViewPagerAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentAddProductBinding
import com.ankursolution.jffmyadmin.databinding.FragmentAddProductVarientBinding
import com.ankursolution.jffmyadmin.databinding.FragmentHomeBinding
import com.ankursolution.jffmyadmin.firebase.UploadFile
import com.ankursolution.jffmyadmin.jffkhata.KhataViewModel
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_product.*

@AndroidEntryPoint
class AddProductVarientsFragment :BaseFragment<FragmentAddProductVarientBinding,HomeViewModel>(),
    UploadFile.OnImageUpload{

    val homeViewModel: HomeViewModel by viewModels()
    val args:AddProductVarientsFragmentArgs by navArgs()
    lateinit var uploadFile: UploadFile
    var myUri: Uri?=null
    var model:Varients?=null

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        myUri = uri
        getViewDataBinding().selectedImage.setImageURI(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadFile = UploadFile(requireContext(), this)

        updateData()
        createProduct()
        checkPermission()


    }

    private fun updateData() {
        if (args.model!="na")
        {
            myUri = Uri.EMPTY
            model = Gson().fromJson(args.model,Varients::class.java)
            getViewDataBinding().model = model
        }
    }

    private fun createProduct() {

        getViewDataBinding().submit.setOnClickListener {
        if (getViewDataBinding().edtVarientName.text.isNullOrEmpty()||getViewDataBinding().edtVarientPrice.text.isNullOrEmpty()||
                myUri==null){
            if (getViewDataBinding().edtVarientName.text.isNullOrEmpty())
                getViewDataBinding().edtVarientName.setError("Please enter varient name")

            if (getViewDataBinding().edtVarientPrice.text.isNullOrEmpty())
                getViewDataBinding().edtVarientPrice.setError("Please enter varient price")

            if (myUri==null)
                showToast("Please select varient image")

        }else{
            getViewDataBinding().submit.visibility = View.GONE
            getViewDataBinding().progressBar.visibility = View.VISIBLE

            if (args.model!="na"&&myUri ==Uri.EMPTY)
            {
                updateProduct(model?.image)
            }else
            uploadFile.uploadonfirestorage(myUri,Constants.PRODUCT_FOLDER,getViewDataBinding().edtVarientName.text.toString())

        }

        }
    }

    private fun updateProduct(image: String?) {

        homeViewModel.updateProductVarient(ProductVarientRequestModel(id=model?.id,name = getViewDataBinding().edtVarientName.text.toString(),image = image,price = getViewDataBinding().edtVarientPrice.text.toString(),type = Constants.UPDATE_REQUEST)).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {
                    showToast("Varient added successfully")
                    findNavController().popBackStack()
                }
            }
        })
    }

    fun uploadProduct(url:String?)
    {
        homeViewModel.createProductVarient(ProductVarientRequestModel(args.pid,getViewDataBinding().edtVarientName.text.toString(),
            url,getViewDataBinding().edtVarientPrice.text.toString())).observe(viewLifecycleOwner,{
                it.getValueOrNull()?.let {
                    if (it.status==1)
                    {
                        showToast("Varient Added Successfully")
                        findNavController().popBackStack()
                    }else{
                        showToast("Try Again! Somthing went wrong")
                        getViewDataBinding().submit.visibility = View.VISIBLE
                        getViewDataBinding().progressBar.visibility = View.GONE
                    }
                }
        })

    }

    fun checkPermission()
    {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            else -> {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    225
                )
            }
        }
        getViewDataBinding().edtImage.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    override fun getLayoutId() = R.layout.fragment_add_product_varient


    override fun getViewModel() = homeViewModel
    override fun getUrl(url: String?) {
        if (args.model!="na")
            updateProduct(url)
            else
        uploadProduct(url)
    }
}