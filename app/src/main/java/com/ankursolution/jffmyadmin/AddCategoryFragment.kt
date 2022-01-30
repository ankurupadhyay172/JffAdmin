package com.ankursolution.jffmyadmin

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
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CategoryModel
import com.ankursolution.jffmyadmin.data.model.CategoryRequestModel
import com.ankursolution.jffmyadmin.databinding.FragmentAddCategoryBinding
import com.ankursolution.jffmyadmin.firebase.UploadFile
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment :BaseFragment<FragmentAddCategoryBinding,HomeViewModel>(),
    UploadFile.OnImageUpload {

    val args:AddCategoryFragmentArgs by navArgs()
    val homeViewModel: HomeViewModel by viewModels()
    lateinit var uploadFile: UploadFile
    var myUri: Uri?=null

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        myUri = uri
        getViewDataBinding().selectedImage.setImageURI(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadFile = UploadFile(requireContext(), this)


        createCategory()
        selectImage()

        if (args.model!="na")
        {
            myUri = Uri.EMPTY
            val model = Gson().fromJson(args.model,CategoryModel.Result::class.java)
            getViewDataBinding().model = model
        }


    }

    private fun selectImage() {

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            else->{
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 225);
            }
        }
        getViewDataBinding().edtImage.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun createCategory() {
        getViewDataBinding().submit.setOnClickListener {
            if (getViewDataBinding().edtCategoryName.text.toString().isNullOrEmpty()||myUri==null)
            {
                if (myUri==null)
                    showToast("Please select category image")

                if (getViewDataBinding().edtCategoryName.text.toString().isNullOrEmpty())
                    getViewDataBinding().edtCategoryName.setError("Please enter category name")

                return@setOnClickListener
            }else {
                getViewDataBinding().submit.visibility = View.GONE
                getViewDataBinding().progressBar.visibility = View.VISIBLE
                if (args.model!="na"&&myUri== Uri.EMPTY)
                {
                    updateCategory(getViewDataBinding().model?.categoryImage)
                }else
                uploadFile.uploadonfirestorage(myUri,Constants.CATEGORY_FOLDER,getViewDataBinding().edtCategoryName.text.toString())
            }
        }
    }

    fun uploadData(url: String?)
    {
        homeViewModel.createCategory(CategoryRequestModel(category_name = getViewDataBinding().edtCategoryName.text.toString(),url))
            .observe(viewLifecycleOwner, {
                it.getValueOrNull()?.let {
                    if (it.status == 1) {
                        showToast("Category Added Successfully")
                        findNavController().popBackStack()
                    }
                }
            })
    }

    override fun getLayoutId() = R.layout.fragment_add_category
    override fun getViewModel() = homeViewModel
    override fun getUrl(url: String?) {
        if (args.model=="na")
        uploadData(url)
        else
            updateCategory(url)
    }

    private fun updateCategory(url: String?) {
            homeViewModel.updateCategory(CategoryRequestModel(category_name = getViewDataBinding().edtCategoryName.text.toString(),id=getViewDataBinding().model?.id,url = url,type = Constants.UPDATE_REQUEST)).observe(viewLifecycleOwner,{
                it?.getValueOrNull().let {
                    if (it?.status==1)
                    {
                        getViewDataBinding().submit.visibility = View.GONE
                        getViewDataBinding().progressBar.visibility = View.VISIBLE
                        findNavController().popBackStack()
                    }else{
                        getViewDataBinding().submit.visibility = View.VISIBLE
                        getViewDataBinding().progressBar.visibility = View.GONE
                    }
                }
            })
    }
}