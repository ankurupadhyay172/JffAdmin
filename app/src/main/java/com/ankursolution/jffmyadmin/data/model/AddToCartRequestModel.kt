package com.ankursolution.jffmyadmin.data.model

data class AddToCartRequestModel(val cust_id:String?,val p_id:String?,val v_id:String?,val emp_id:String?=null,val quan:String?,val type:String) {
}