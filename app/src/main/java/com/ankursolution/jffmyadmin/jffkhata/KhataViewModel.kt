package com.ankursolution.jffmyadmin.jffkhata

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ankursolution.jffmyadmin.data.model.AddUserRequestModel
import com.ankursolution.jffmyadmin.retrofit.LoadState
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



    fun getAllJffKhataUsers()= liveData(Dispatchers.IO){
        khataRepository.getAllKhataUsers().toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
        }.collect {
            emit(it)
        }
    }



    fun addKhataUser(addUserRequestModel: AddUserRequestModel)= liveData(Dispatchers.IO){
        khataRepository.addKhataUser(addUserRequestModel).toLoadingState().catch { e->
            Log.d("mylogerror",""+e.message)
        }.collect {
            emit(it)
        }
    }


}