package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel (@SerializedName("status") val status : Int,
                         @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("category") val category : String,
        @SerializedName("productName") val productName : String,
        @SerializedName("productDescription") val productDescription : String?,
        @SerializedName("varient") val varients : List<Varients>

    )


}

data class Varients (
    @SerializedName("id") val id : String,
    @SerializedName("varientName") val varientName : String,
    @SerializedName("varientPrice") val varientPrice : String,
    @SerializedName("image") val image : String?,
    @SerializedName("pid") val pid : String?

)


