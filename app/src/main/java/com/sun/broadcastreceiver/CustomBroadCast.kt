package com.sun.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CustomBroadCast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("DEMO",p1?.action.toString())
        when (p1?.action.toString()) {
            Keys.ACTION_NORMAL -> {
                val message = p1!!.getStringExtra(Keys.NAME)
                Toast.makeText(p0, message, Toast.LENGTH_LONG).show()
            }
        }
    }

}