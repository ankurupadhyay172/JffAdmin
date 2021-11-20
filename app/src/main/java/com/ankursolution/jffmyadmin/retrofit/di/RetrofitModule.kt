package com.ankursolution.jffmyadmin.retrofit.di

import com.ankursolution.jffmyadmin.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesBaseUrl():String = "https://ankurupadhyay.com/humarabazar/apis/"



    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseurl:String): Retrofit = Retrofit.Builder()
        .baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create())
        .build()



    @Provides
    fun providesPostApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



}