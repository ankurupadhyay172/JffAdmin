package com.ankursolution.jffmyadmin.jffkhata

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ankursolution.jffmyadmin.data.model.AddTransactionRequestModel
import com.ankursolution.jffmyadmin.data.model.AddUserRequestModel
import com.ankursolution.jffmyadmin.data.model.KhataTransactionRequestModel
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
    private val khataRepository: KhataRepository) : ViewModel(){

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


    fun getKhataTransaction(khataTransactionRequestModel: KhataTransactionRequestModel)= liveData(Dispatchers.IO){
        khataRepository.getUserTransaction(khataTransactionRequestModel).toLoadingState().catch { e->
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
}


