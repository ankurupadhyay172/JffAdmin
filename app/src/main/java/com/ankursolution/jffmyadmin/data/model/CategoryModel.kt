package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class CategoryModel (@SerializedName("status") val status : Int,
                          @SerializedName("result") val result : List<Result>){


    data class Result (

        @SerializedName("id") val id : String,
        @SerializedName("categoryName") val categoryName : String,
        @SerializedName("categoryImage") val categoryImage : String,
        @SerializedName("categoryDescription") val categoryDescription : String?,
        @SerializedName("orderNo") val orderNo : String

    )
}