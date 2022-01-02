package com.ankursolution.jffmyadmin.jffkhata

import com.ankursolution.jffmyadmin.data.model.*
import com.ankursolution.jffmyadmin.retrofit.di.HomeServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(val homeServiceImp: HomeServiceImp) {



    suspend fun getAllPendingOrders(commonRequestModel: CommonRequestModel):Flow<OrderResultModel> = flow {
        val response = homeServiceImp.api.getAllPendingOrders(commonRequestModel)
        emit(response)
    }


    suspend fun getSinglePendingOrders(commonRequestModel: CommonRequestModel):Flow<SingleOrderResult> = flow {
        val response = homeServiceImp.api.getSinglePendingOrders(commonRequestModel)
        emit(response)
    }


    suspend fun updateOrder(orderUpdateRequestModel: OrderUpdateRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateOrder(orderUpdateRequestModel)
        emit(response)
    }


    suspend fun getCategory():Flow<CategoryModel> = flow {
        val response = homeServiceImp.api.getCategory()
        emit(response)
    }

    suspend fun getProducts(commonRequestModel: CommonRequestModel):Flow<ProductModel> = flow {
        val response = homeServiceImp.api.getProducts(commonRequestModel)
        emit(response)
    }

}