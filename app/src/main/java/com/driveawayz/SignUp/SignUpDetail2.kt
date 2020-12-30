package com.driveawayz.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.driveawayz.R

class SignUpDetail2 : AppCompatActivity() {

    private lateinit var back: ImageButton
    private lateinit var nextbt: Button
    private lateinit var make_et : EditText
    private lateinit var type_et : EditText
    private lateinit var year_et : EditText
    private lateinit var transsmisuion_et : EditText
    private lateinit var state_et : EditText
    private lateinit var licenceplate_et : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail2)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        nextbt.setOnClickListener {

                when{
                    make_et.text.isEmpty() -> {
                        make_et.requestFocus()
                        make_et.error = "Enter make"
                    }

                    type_et.text.isEmpty() -> {
                        type_et.requestFocus()
                        type_et.error = "Enter type"
                    }

                    year_et.text.isEmpty() -> {
                        year_et.requestFocus()
                        year_et.error = "Enter year"
                    }

                    transsmisuion_et.text.isEmpty() -> {
                        transsmisuion_et.requestFocus()
                        transsmisuion_et.error = "Enter transmission"
                    }

                    licenceplate_et.text.isEmpty() -> {
                        licenceplate_et.requestFocus()
                        licenceplate_et.error = "Enter licence plate"
                    }

                    state_et.text.isEmpty() -> {
                        state_et.requestFocus()
                        state_et.error = "Enter state"
                    } else -> {
                    startActivity(Intent(this, CompleteSignUp::class.java))
                }
                }


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
        nextbt = findViewById(R.id.nextbt)
        make_et = findViewById(R.id.make_et)
        type_et = findViewById(R.id.type_et)
        year_et = findViewById(R.id.year_et)
        transsmisuion_et = findViewById(R.id.transsmisuion_et)
        state_et = findViewById(R.id.state_et)
        licenceplate_et = findViewById(R.id.licenceplate_et)
    }
}