package com.driveawayz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.driveawayz.Login.LoginScreen
import com.driveawayz.SignUp.signupphone.SignUpPhoneNo

class MainActivity : AppCompatActivity() {

    private lateinit var login_bt : Button
    private lateinit var signup_bt : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        findIds()
        lisenters()
    }

    private fun lisenters() {
        login_bt.setOnClickListener { startActivity(Intent(this,LoginScreen::class.java)) }
        signup_bt.setOnClickListener { startActivity(Intent(this, SignUpPhoneNo::class.java)) }
    }

    private fun findIds() {
        login_bt = findViewById(R.id.login_bt)
        signup_bt = findViewById(R.id.signup_bt)
    }
}