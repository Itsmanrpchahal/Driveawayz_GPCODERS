package com.driveawayz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.driveawayz.Login.LoginScreen
import com.driveawayz.SignUp.SignUpDetail1

class MainActivity : AppCompatActivity() {

    private lateinit var login_bt : Button
    private lateinit var signup_bt : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        login_bt.setOnClickListener { startActivity(Intent(this,LoginScreen::class.java)) }
        signup_bt.setOnClickListener { startActivity(Intent(this, SignUpDetail1::class.java)) }
    }

    private fun findIds() {
        login_bt = findViewById(R.id.login_bt)
        signup_bt = findViewById(R.id.signup_bt)
    }
}