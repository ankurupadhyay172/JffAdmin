package com.ankursolution.jffmyadmin.jffkhata

import com.ankursolution.jffmyadmin.data.model.AddUserRequestModel
import com.ankursolution.jffmyadmin.data.model.CommonResponseModel
import com.ankursolution.jffmyadmin.data.model.JffKhataUserModel
import com.ankursolution.jffmyadmin.retrofit.di.HomeServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class KhataRepository @Inject constructor(val homeServiceImp: HomeServiceImp) {



    suspend fun getAllKhataUsers():Flow<JffKhataUserModel> = flow {
        val response = homeServiceImp.api.getAllKhataUsers()
        emit(response)
    }

    suspend fun addKhataUser(addUserRequestModel: AddUserRequestModel):Flow<CommonResponseModel> = flow {
        val response = homeServiceImp.api.addKhataCustomer(addUserRequestModel)
        emit(response)
    }


}