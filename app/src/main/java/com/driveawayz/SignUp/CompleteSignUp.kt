package com.driveawayz.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.driveawayz.R
import com.driveawayz.dashboard.Dashboard

class CompleteSignUp : AppCompatActivity() {
    private lateinit var back: ImageButton
    private lateinit var completebt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_sign_up)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        completebt.setOnClickListener { startActivity(Intent(this,Dashboard::class.java)) }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        completebt =findViewById(R.id.completebt)
    }
}