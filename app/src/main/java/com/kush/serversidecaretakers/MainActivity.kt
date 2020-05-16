package com.kush.serversidecaretakers

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clickMe = findViewById<TextView>(R.id.clickMeTV)
        clickMe.setOnClickListener {
            val goToSendNotification = Intent(this@MainActivity, SendNotification::class.java)
            startActivity(goToSendNotification)
        }
    }

}