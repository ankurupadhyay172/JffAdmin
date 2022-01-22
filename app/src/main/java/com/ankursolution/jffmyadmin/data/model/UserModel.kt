package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (@SerializedName("status") val status : Int,
                      @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("userid") val userid : String,
        @SerializedName("name") val name : String,
        @SerializedName("image") val image : String,
        @SerializedName("password") val password : String,
        @SerializedName("timestamp") val timestamp : String


        )

}