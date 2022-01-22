package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class CartModel (@SerializedName("status") val status : Int,
                         @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("cust_id") val cust_id : String,
        @SerializedName("p_id") val p_id : String,
        @SerializedName("v_id") val v_id : String?,
        @SerializedName("emp_id") val emp_id : String,
        @SerializedName("quan") val quan : String

    )


}

