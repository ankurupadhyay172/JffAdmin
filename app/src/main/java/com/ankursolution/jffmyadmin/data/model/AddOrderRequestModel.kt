package com.ankursolution.jffmyadmin.data.model

data class AddOrderRequestModel(val userId:String?, val userName:String?,val deliveryCharge:String?="0",
                                val totalAmount:String?,val deliveryAddress:String?=null,val orderType:String?,
                                val userInstruction:String?=null,val tableNo:String?=null,val orderStatus:String?,
                                val products:List<CartOrderResult.Result>) {


}