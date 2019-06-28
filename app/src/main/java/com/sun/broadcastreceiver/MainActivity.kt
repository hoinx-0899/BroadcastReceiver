package com.sun.broadcastreceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.ComponentName

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var myBroadCast: MyBroadCast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerListener()
    }

    private fun registerListener() {
        btnSend.setOnClickListener(this)
        btnSendAnother.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        myBroadCast = MyBroadCast()
        eventInternet()
        registerReceiver(myBroadCast, filter)


    }

    private fun eventInternet() {
        myBroadCast.setOnInternetConnect {
            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show()
        }
        myBroadCast.setOnInternetDisconnect {
            Toast.makeText(this, "Disconnected", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::myBroadCast.isInitialized) {
            unregisterReceiver(myBroadCast)
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btnSend -> {
                if(TextUtils.isEmpty(edtMessage.text.toString())){
                    Toast.makeText(this,getString(R.string.empty_message),Toast.LENGTH_LONG).show()
                }else{

                    val intent = Intent(this,CustomBroadCast::class.java)
                    intent.action=Keys.ACTION_NORMAL
                    intent.putExtra(Keys.NAME,edtMessage.text.toString())
                    sendBroadcast(intent)
                }

            }
            R.id.btnSendAnother->{
                if(TextUtils.isEmpty(edtMessage.text.toString())) {
                    Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_LONG).show()
                }else{
                    val intent = Intent()
                    intent.action = Keys.ACTION_ANOTHER
                    intent.putExtra(Keys.NAME,edtMessage.text.toString())
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                    intent.component = ComponentName("com.sun.broadcastreceiverb", "com.sun.broadcastreceiverb.CustomBroadCast")
                    sendBroadcast(intent)
                }
            }
        }
    }
}
