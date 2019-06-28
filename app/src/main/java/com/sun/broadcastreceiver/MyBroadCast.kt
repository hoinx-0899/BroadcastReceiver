package com.sun.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
class MyBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (NetworkUtil.isNetworkAvailable()) {
            if (::onInternetConnect.isInitialized) {
                onInternetConnect.invoke()
            }
        } else {
            if (::onInternetDisconnect.isInitialized) {
                onInternetDisconnect.invoke()
            }
        }
    }

    private lateinit var onInternetConnect: () -> Unit
    fun setOnInternetConnect(onInternetConnect: () -> Unit) {
        this.onInternetConnect = onInternetConnect
    }

    private lateinit var onInternetDisconnect: () -> Unit
    fun setOnInternetDisconnect(onInternetDisconnect: () -> Unit) {
        this.onInternetDisconnect = onInternetDisconnect
    }

}