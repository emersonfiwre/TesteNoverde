package com.emersonfiwre.testenoverde.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emersonfiwre.testenoverde.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) { // initial transaction should be wrapped like this
            //test shared preferences
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_root, WelcomeFragment())
                .commitAllowingStateLoss()
        }

    }
}