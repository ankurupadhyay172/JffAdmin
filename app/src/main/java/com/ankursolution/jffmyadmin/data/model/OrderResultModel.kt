package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class OrderResultModel (@SerializedName("status") val status : Int,
                             @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("user_id") val user_id : String,
        @SerializedName("user_name") val user_name : String?,
        @SerializedName("delivery_charge") val delivery_charge : String?,
        @SerializedName("total_amount") val total_amount : String,
        @SerializedName("table_no") val table_no : String,
        @SerializedName("delivery_address") val delivery_address : String,
        @SerializedName("order_type") val order_type : String,
        @SerializedName("payment_method") val payment_method : String,
        @SerializedName("payment_status") val payment_status : String,
        @SerializedName("order_status") val order_status : String,
        @SerializedName("upi_reference") val upi_reference : String,
        @SerializedName("device_token") val device_token : String,
        @SerializedName("order_timestamp") val order_timestamp : String,

    )
}