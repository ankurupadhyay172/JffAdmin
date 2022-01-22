package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class CartOrderResult (@SerializedName("status") val status : Int,
                            @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("cart_id") val cart_id : String,
        @SerializedName("cust_id") val cust_id : String,
        @SerializedName("p_id") val p_id : String?,
        @SerializedName("v_id") val v_id : String?,
        @SerializedName("emp_id") val emp_id : String,
        @SerializedName("quan") val quan : String,
        @SerializedName("category") val category : String?,
        @SerializedName("productName") val productName : String,
        @SerializedName("productDescription") val productDescription : String,
        @SerializedName("varientName") val varientName : String,
        @SerializedName("varientPrice") val varientPrice : String,
        @SerializedName("image") val image : String,

        )

}