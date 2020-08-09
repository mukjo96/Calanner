package com.example.calanner

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class splash_activity : AppCompatActivity() {

    //loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 3000 // 1sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_splash_activity)

        Handler().postDelayed({
            //be executed once the timer is over
            //start login activity

            startActivity(Intent(this, LoginActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)
    }
}
