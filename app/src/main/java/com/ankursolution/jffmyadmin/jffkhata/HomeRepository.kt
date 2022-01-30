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


    suspend fun getOrderHistory(commonRequestModel: CommonRequestModel):Flow<OrderResultModel> = flow {
        val response = homeServiceImp.api.getOrderHistory(commonRequestModel)
        emit(response)
    }


    suspend fun getCartItem(commonRequestModel: CommonRequestModel):Flow<CartOrderResult> = flow {
        val response = homeServiceImp.api.getCartItem(commonRequestModel)
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


    suspend fun updateCart(commonRequestModel: UpdateCartRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateCartData(commonRequestModel)
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

    suspend fun updateProducts(productRequestModel: ProductRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateProducts(productRequestModel)
        emit(response)
    }

    suspend fun getProductDetails(commonRequestModel: CommonRequestModel):Flow<ProductModel> = flow {
        val response = homeServiceImp.api.getProductDetails(commonRequestModel)
        emit(response)
    }

    suspend fun readProductVarient(commonRequestModel: CommonRequestModel):Flow<ProductVarientModel> = flow {
        val response = homeServiceImp.api.readProductVarient(commonRequestModel)
        emit(response)
    }

    suspend fun updateProductVarient(productVarientRequestModel: ProductVarientRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateProductVarient(productVarientRequestModel)
        emit(response)
    }

    suspend fun createCategory(categoryRequestModel: CategoryRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.createCategory(categoryRequestModel)
        emit(response)
    }

    suspend fun updateCategory(categoryRequestModel: CategoryRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateCategory(categoryRequestModel)
        emit(response)
    }

    suspend fun createProduct(createProductRequestModel: ProductRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.createProduct(createProductRequestModel)
        emit(response)
    }

    suspend fun createProductVarient(productVarientRequestModel: ProductVarientRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.createProductVarient(productVarientRequestModel)
        emit(response)
    }

    suspend fun addToCart(addToCartRequestModel: AddToCartRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.addToCart(addToCartRequestModel)
        emit(response)
    }

    suspend fun loginUser(loginRequestModel: LoginRequestModel):Flow<UserModel> = flow {
        val response = homeServiceImp.api.loginUser(loginRequestModel)
        emit(response)
    }


    suspend fun addOrder(addOrderRequestModel: AddOrderRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.addOrder(addOrderRequestModel)
        emit(response)
    }
    suspend fun getCart(commonRequestModel: CommonRequestModel):Flow<CartModel> = flow {
        val response = homeServiceImp.api.getCartData(commonRequestModel)
        emit(response)
    }


    suspend fun completeJffOrder(updateRequestModel: OrderUpdateRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.completeJffOrder(updateRequestModel)
        emit(response)
    }

    suspend fun updateOrderItem(orderItemRequestModel: OrderItemRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.updateorderitem(orderItemRequestModel)
        emit(response)
    }

}