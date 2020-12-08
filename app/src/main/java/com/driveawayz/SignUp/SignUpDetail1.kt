package com.driveawayz.SignUp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.driveawayz.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SignUpDetail1 : AppCompatActivity() {

    private lateinit var back : ImageButton
    private lateinit var nextbt : Button
    private lateinit var dob_et : EditText
    private lateinit var datetime : String
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail1)

        findIds()
        lisenters()
    }

    private fun lisenters() {
        back.setOnClickListener { onBackPressed() }
        nextbt.setOnClickListener { startActivity(Intent(this, SignUpDetail2::class.java)) }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)


                updateDateInView()
            }
        }

        dob_et.setOnClickListener {
            DatePickerDialog(
                this, R.style.DialogTheme,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
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


        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // create an OnDateSetListener

        back = findViewById(R.id.back)
        nextbt = findViewById(R.id.nextbt)
        dob_et = findViewById(R.id.dob_et)
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1,Locale.UK)
        datetime = sdf1.format(c.time)
        dob_et.setText(sdf.format(c.time))
    }

}