package com.driveawayz.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.driveawayz.Constant.BaseClass
import com.driveawayz.OTPScreen.OTPScreen
import com.driveawayz.R
import com.driveawayz.SignUp.SignUpDetail1

class LoginScreen : BaseClass() {

    private lateinit var back : ImageButton
    private lateinit var signInbt : Button
    private lateinit var donthaveaccountbt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        back.setOnClickListener { onBackPressed() }
        signInbt.setOnClickListener { startActivity(Intent(this,OTPScreen::class.java)) }
        donthaveaccountbt.setOnClickListener { startActivity(Intent(this, SignUpDetail1::class.java)) }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        signInbt = findViewById(R.id.signInbt)
        donthaveaccountbt = findViewById(R.id.donthaveaccountbt)
    }
}