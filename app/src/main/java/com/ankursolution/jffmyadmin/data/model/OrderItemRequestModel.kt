package com.ankursolution.jffmyadmin.data.model

data class OrderItemRequestModel(val id:String?, val user_id:String?=null,val pid:String?=null,val vid:String?=null,val quan:Int=0,val type:String?) {
}