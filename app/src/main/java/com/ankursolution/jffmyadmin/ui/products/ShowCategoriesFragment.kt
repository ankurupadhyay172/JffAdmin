package com.ankursolution.jffmyadmin.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.CategoryRequestModel
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentShowCategoryBinding
import com.ankursolution.jffmyadmin.firebase.UploadFile
import com.ankursolution.jffmyadmin.ui.HomeViewModel
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowCategoriesFragment : BaseFragment<FragmentShowCategoryBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: CategoryAdapter

    lateinit var uploadFile: UploadFile


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvCategory.adapter = adapter
        getCategoryData()
        deleteCategory()
        getViewDataBinding().addCategory.setOnClickListener {
            findNavController().navigate(ShowCategoriesFragmentDirections.actionShowCategoriesFragmentToAddCategoryFragment())

        }

    }

    private fun deleteCategory() {

        adapter.update={
            findNavController().navigate(ShowCategoriesFragmentDirections.actionShowCategoriesFragmentToAddCategoryFragment(model = it))
        }

        adapter.delete= {id,name->
            val photoRef = FirebaseStorage.getInstance().getReference(Constants.CATEGORY_FOLDER).child(name!!)
            photoRef.delete().addOnSuccessListener {
            updateData(id,name)
            }.addOnFailureListener {
                updateData(id,name)
                //showToast(""+it.message)
            }
        }
    }

    fun updateData(id: String?, name: String)
    {
        homeViewModel.updateCategory(CategoryRequestModel(id = id,type = Constants.DELETE_REQUEST)).observe(viewLifecycleOwner,{
            it.getValueOrNull()?.let {
                if (it.status==1)
                {
                    showToast("Category deleted successfully")
                    getCategoryData()
                }else{
                    showToast("Somthing went wrong please try again later")
                }
            }
        })
    }

    private fun getCategoryData() {

        homeViewModel.getCategory().observe(viewLifecycleOwner, Observer {
            it.getValueOrNull().let {
                if (it?.status==1)
                adapter.submitList(it.result)

            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_show_category
    override fun getViewModel() = homeViewModel


}