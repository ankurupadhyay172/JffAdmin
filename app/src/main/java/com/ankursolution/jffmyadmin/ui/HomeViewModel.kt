package com.ankursolution.jffmyadmin.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.ankursolution.jffmyadmin.base.BaseViewModel
import com.ankursolution.jffmyadmin.data.model.CommonRequestModel
import com.ankursolution.jffmyadmin.data.model.OrderUpdateRequestModel
import com.ankursolution.jffmyadmin.jffkhata.HomeRepository
import com.ankursolution.jffmyadmin.jffkhata.KhataRepository
import com.ankursolution.jffmyadmin.retrofit.LoadingState
import com.ankursolution.jffmyadmin.utils.ext.toLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext val context: Context,
                                        private val homeRepository: HomeRepository):BaseViewModel() {


    public val loadState = MutableLiveData<LoadingState>(LoadingState.Loading)

    fun getAllPendingOrders(status:String?)= liveData(Dispatchers.IO){
        homeRepository.getAllPendingOrders(CommonRequestModel(status)).toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }



    fun getSinglePendingOrders(status:String?)= liveData(Dispatchers.IO){
        homeRepository.getSinglePendingOrders(CommonRequestModel(status)).toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }

    fun updateOrder(orderUpdateRequestModel: OrderUpdateRequestModel)= liveData(Dispatchers.IO){
        homeRepository.updateOrder(orderUpdateRequestModel).toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }

    fun getCategory()= liveData(Dispatchers.IO){
        homeRepository.getCategory().toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }


    fun getProduct(commonRequestModel: CommonRequestModel)= liveData(Dispatchers.IO){
        homeRepository.getProducts(commonRequestModel).toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }
}