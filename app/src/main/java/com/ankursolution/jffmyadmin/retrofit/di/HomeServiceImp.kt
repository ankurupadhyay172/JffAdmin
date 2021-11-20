package com.ankursolution.jffmyadmin.retrofit.di

import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class HomeServiceImp @Inject constructor(private val apiService: ApiService) {


    companion object{
        var retrofit = Retrofit.Builder().baseUrl("https://ankurupadhyay.com/jff/apis/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api:ApiService = retrofit.create(ApiService::class.java)
}