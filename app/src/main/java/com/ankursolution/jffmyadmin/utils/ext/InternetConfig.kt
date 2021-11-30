package com.ankursolution.jffmyadmin.utils.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Context.checkConnect():Flow<Boolean> = callbackFlow {

    val callback = object :ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            offer(true)
        }
        override fun onLost(network: Network) {
            super.onLost(network)
            offer(false)
        }
    }

    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    manager.registerNetworkCallback(NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build(),callback)

    awaitClose {
        manager.unregisterNetworkCallback(callback)
    }

}