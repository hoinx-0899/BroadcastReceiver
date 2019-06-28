package com.sun.broadcastreceiverb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val name = it.getStringExtra(Keys.NAME)
            Toast.makeText(this, "MainActivity$name",Toast.LENGTH_LONG).show()

        }
    }
}
