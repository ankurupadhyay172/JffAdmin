package com.ankursolution.jffmyadmin.data.model

import com.google.gson.annotations.SerializedName

data class ProductVarientModel (@SerializedName("status") val status : Int,
                         @SerializedName("result") val result : List<Varients>){




}




