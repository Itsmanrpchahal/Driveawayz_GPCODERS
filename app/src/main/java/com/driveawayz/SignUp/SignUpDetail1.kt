package com.driveawayz.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.driveawayz.R

class SignUpDetail1 : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var nextbt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail1)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        back.setOnClickListener { onBackPressed() }
        nextbt.setOnClickListener { startActivity(Intent(this, SignUpDetail2::class.java)) }
    }

    private fun findIds() {
        back = findViewById(R.id.back)
        nextbt = findViewById(R.id.nextbt)
    }
}