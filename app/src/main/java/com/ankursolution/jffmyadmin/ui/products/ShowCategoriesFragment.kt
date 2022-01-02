package com.ankursolution.jffmyadmin.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.base.BaseFragment
import com.ankursolution.jffmyadmin.data.model.adapter.CategoryAdapter
import com.ankursolution.jffmyadmin.databinding.FragmentShowCategoryBinding

import com.ankursolution.jffmyadmin.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowCategoriesFragment : BaseFragment<FragmentShowCategoryBinding,HomeViewModel>() {
    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var adapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().rvCategory.adapter = adapter
        getCategoryData()


    }

    private fun getCategoryData() {
        homeViewModel.getCategory().observe(viewLifecycleOwner, Observer {
            it.getValueOrNull().let {
                if (it?.status==1)
                adapter.submitList(it?.result)

            }
        })
    }

    override fun getLayoutId() = R.layout.fragment_show_category
    override fun getViewModel() = homeViewModel


}