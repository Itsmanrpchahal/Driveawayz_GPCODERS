package com.driveawayz.SignUp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.driveawayz.R
import com.driveawayz.dashboard.Dashboard
import java.text.SimpleDateFormat
import java.util.*

class CompleteSignUp : AppCompatActivity() {
    private lateinit var back: ImageButton
    private lateinit var completebt: Button
    private lateinit var monthcard: EditText
    private lateinit var yearcard: EditText
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_sign_up)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        completebt.setOnClickListener { startActivity(Intent(this, Dashboard::class.java)) }

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
        monthcard.setOnClickListener {
            DatePickerDialog(
                this, R.style.DialogTheme,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        yearcard.setOnClickListener {
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


    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1, Locale.UK)

        val date = sdf.format(c.time)
        val separate1 = date.split("/".toRegex()).map { it.trim() }

        monthcard.setText(separate1[1])
        yearcard.setText(separate1[2])
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
        completebt = findViewById(R.id.completebt)
        monthcard = findViewById(R.id.monthcard)
        yearcard = findViewById(R.id.yearcard)
    }
}