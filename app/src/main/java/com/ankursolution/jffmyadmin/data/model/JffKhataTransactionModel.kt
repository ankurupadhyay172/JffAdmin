package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class JffKhataTransactionModel (@SerializedName("status") val status : Int,
                                     @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("user_id") val user_id : String,
        @SerializedName("give") val give : String,
        @SerializedName("got") val got : String,
        @SerializedName("timestamp") val timestamp : String
    )
}