package com.ankursolution.jffmyadmin.jffkhata

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ankursolution.jffmyadmin.base.BaseViewModel
import com.ankursolution.jffmyadmin.data.model.*
import com.ankursolution.jffmyadmin.retrofit.LoadState
import com.ankursolution.jffmyadmin.retrofit.LoadingState
import com.ankursolution.jffmyadmin.utils.ext.toLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class KhataViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val khataRepository: KhataRepository) : BaseViewModel(){

    public val loadState = MutableLiveData<LoadingState>(LoadingState.Loading)

    fun getAllJffKhataUsers()= liveData(Dispatchers.IO){
        khataRepository.getAllKhataUsers().toLoadingState().catch { e->
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }



    fun addKhataUser(addUserRequestModel: AddUserRequestModel)= liveData(Dispatchers.IO){
        khataRepository.addKhataUser(addUserRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }


    fun updateKhataUser(addUserRequestModel: AddUserRequestModel)= liveData(Dispatchers.IO){
        khataRepository.updateKhataUser(addUserRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }


    fun getKhataTransaction(khataTransactionRequestModel: KhataTransactionRequestModel)= liveData(Dispatchers.IO){
        khataRepository.getUserTransaction(khataTransactionRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }



    fun getSingleKhataTransaction(commonRequestModel: CommonRequestModel)= liveData(Dispatchers.IO){
        khataRepository.getSingleUserTransaction(commonRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }


    fun deleteSingleKhataTransaction(commonRequestModel: CommonRequestModel)= liveData(Dispatchers.IO){
        khataRepository.deleteSingleUserTransaction(commonRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }

    fun addKhataTransaction(addTransactionRequestModel: AddTransactionRequestModel)= liveData(Dispatchers.IO){
        khataRepository.addUserTransaction(addTransactionRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }

    fun updateAmount(updateAmountRequestModel: UpdateAmountRequestModel)= liveData(Dispatchers.IO){
        khataRepository.updateAmount(updateAmountRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }


    fun deleteKhataUser(commonRequestModel: CommonRequestModel)= liveData(Dispatchers.IO){
        khataRepository.deleteKhataUser(commonRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
            loadState.postValue(LoadingState.Error(e))
        }.collect {
            emit(it)
        }
    }
}


