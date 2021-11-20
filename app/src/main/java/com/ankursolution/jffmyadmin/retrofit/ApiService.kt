package com.ankursolution.jffmyadmin.retrofit

import com.ankursolution.jffmyadmin.data.model.AddUserRequestModel
import com.ankursolution.jffmyadmin.data.model.CommonResponseModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("readallkhatausers.php")
    suspend fun getAllKhataUsers(): JffKhataUserModel


    @POST("addkhatauser.php")
    suspend fun addKhataCustomer(@Body addUserRequestModel: AddUserRequestModel):CommonResponseModel


//    @POST("readallkhatatransaction.php")
//    suspend fun addKhataCustomer(@Body addUserRequestModel: AddUserRequestModel):CommonResponseModel


}