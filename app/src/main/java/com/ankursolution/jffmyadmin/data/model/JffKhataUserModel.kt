package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class JffKhataUserModel (@SerializedName("status") val status : Int,
                         @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("name") val name : String,
        @SerializedName("mobileno") val mobileno : String,
        @SerializedName("address") val address : String,
        @SerializedName("type") val type : String,
        @SerializedName("last_transaction") val last_transaction : String,
        @SerializedName("timestamp") val timestamp : String
    )
}