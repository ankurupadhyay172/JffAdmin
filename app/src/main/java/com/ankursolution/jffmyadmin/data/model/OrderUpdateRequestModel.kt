package com.ankursolution.jffmyadmin.data.model

data class OrderUpdateRequestModel(val id:String?, val address:String?=null,
                                   val instruction:String?=null,val table_no:String?=null,
                                    val discount:String?=null,val status:String?=null)