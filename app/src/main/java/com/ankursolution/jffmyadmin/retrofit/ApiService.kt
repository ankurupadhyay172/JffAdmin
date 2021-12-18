package com.ankursolution.jffmyadmin.retrofit

import com.ankursolution.jffmyadmin.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("readallkhatausers.php")
    suspend fun getAllKhataUsers(): JffKhataUserModel


    @POST("addkhatauser.php")
    suspend fun addKhataCustomer(@Body addUserRequestModel: AddUserRequestModel):CommonResponseModel


    @POST("readallkhatatransaction.php")
    suspend fun getKhataTransaction(@Body khataTransactionRequestModel: KhataTransactionRequestModel):JffKhataTransactionModel


    @POST("addkhatatransaction.php")
    suspend fun addKhataTransaction(@Body addTransactionRequestModel: AddTransactionRequestModel):CommonResponseModel


    @POST("readsingletransaction.php")
    suspend fun getSingleKhataTransaction(@Body commonRequestModel: CommonRequestModel):JffKhataTransactionModel


    @POST("deletekhatatransaction.php")
    suspend fun deleteSingleKhataTransaction(@Body commonRequestModel: CommonRequestModel):CommonResponseModel

}