package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class SingleOrderResult (@SerializedName("order_details") val result : List<Result>,
                              @SerializedName("items") val items : List<Items>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("user_id") val user_id : String,
        @SerializedName("user_name") val user_name : String?,
        @SerializedName("delivery_charge") val delivery_charge : String?,
        @SerializedName("total_amount") val total_amount : String,
        @SerializedName("table_no") val table_no : String,
        @SerializedName("discount") val discount : String?,
        @SerializedName("delivery_address") val delivery_address : String,
        @SerializedName("order_type") val order_type : String,
        @SerializedName("user_instruction") val user_instruction : String,
        @SerializedName("payment_method") val payment_method : String,
        @SerializedName("payment_status") val payment_status : String,
        @SerializedName("order_status") val order_status : String,
        @SerializedName("upi_reference") val upi_reference : String,
        @SerializedName("device_token") val device_token : String,
        @SerializedName("order_timestamp") val order_timestamp : String,

    )



    data class Items (

        @SerializedName("id") val id : String,
        @SerializedName("user_id") val user_id : String,
        @SerializedName("pid") val pid : String?,
        @SerializedName("product_name") val product_name : String?,
        @SerializedName("product_price") val product_price : String,
        @SerializedName("product_mrp") val product_mrp : String,
        @SerializedName("product_image") val product_image : String,
        @SerializedName("product_size") val product_size : String,
        @SerializedName("quan") val quan : String,

        )

}