package com.ankursolution.jffmyadmin.utils.ext

import androidx.annotation.StringRes
import com.ankursolution.jffmyadmin.R
import com.ankursolution.jffmyadmin.data.model.AppError
import retrofit2.HttpException


@StringRes
fun AppError.stringRes() = when (this){
    is AppError.ApiException.NetworkException-> R.string.error_network
    is AppError.ApiException.NoConnectivityException->R.string.error_no_internet_connection
    is AppError.ApiException.ServerException->R.string.error_server
    is AppError.ApiException.SessionNotFoundException -> R.string.error_unknown
    is AppError.ApiException.UnknownException -> R.string.error_unknown
    else -> R.string.error_unknown
}


fun Throwable?.toAppError():AppError?{
    return when(this){
        null->null
        is AppError->this
        is NoConnectivityException -> AppError.ApiException.NoConnectivityException(this)
        else ->AppError.UnknownException(this)
    }
}






