package com.driveawayz.Login

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.driveawayz.Constant.BaseClass
import com.driveawayz.OTPScreen.OTPScreen
import com.driveawayz.R
import com.driveawayz.SignUp.SignUpDetail1
import com.driveawayz.dashboard.Dashboard


class LoginScreen : BaseClass() {

    private lateinit var back: ImageButton
    private lateinit var signInbt: Button
    private lateinit var donthaveaccountbt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        back.setOnClickListener { onBackPressed() }
        signInbt.setOnClickListener { startActivity(Intent(this, Dashboard::class.java)) }
        donthaveaccountbt.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SignUpDetail1::class.java
                )
            )
        }
    }

    private fun findIds() {
        val window: Window = getWindow()
// clear FLAG_TRANSLUCENT_STATUS flag:
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
// finally change the color
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorLightTheme))
        back = findViewById(R.id.back)
        signInbt = findViewById(R.id.signInbt)
        donthaveaccountbt = findViewById(R.id.donthaveaccountbt)
    }
}