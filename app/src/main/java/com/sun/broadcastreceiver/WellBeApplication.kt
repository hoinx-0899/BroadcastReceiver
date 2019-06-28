package com.sun.broadcastreceiver

import android.app.Application
import android.content.Context


class WellBeApplication : Application() {

    companion object {
        lateinit var instance: WellBeApplication
            private set
        val context: Context
            get() {
                return instance.applicationContext
            }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }




}