package com.sun.broadcastreceiver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.IntentFilter



class SecondActivity :AppCompatActivity(){
    private lateinit var customBroadCast: CustomBroadCast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    override fun onResume() {
        super.onResume()
        customBroadCast = CustomBroadCast()
        val filter = IntentFilter(Keys.ACTION_NORMAL)
        registerReceiver(customBroadCast, filter)
    }

    override fun onStop() {
        super.onStop()
        if(::customBroadCast.isInitialized){
            unregisterReceiver(customBroadCast)
        }
    }

}