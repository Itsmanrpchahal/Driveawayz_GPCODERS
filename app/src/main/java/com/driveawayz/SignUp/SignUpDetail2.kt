package com.driveawayz.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.driveawayz.R

class SignUpDetail2 : AppCompatActivity() {

    private lateinit var back: ImageButton
    private lateinit var nextbt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail2)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        nextbt.setOnClickListener { startActivity(Intent(this, CompleteSignUp::class.java)) }
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
        nextbt = findViewById(R.id.nextbt)
    }
}