package com.driveawayz.SignUp

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Card
import com.stripe.android.model.PaymentIntent
import com.stripe.android.view.CardInputWidget
import com.stripe.model.PaymentMethod
import com.stripe.model.PaymentMethod.create
import java.text.SimpleDateFormat
import java.util.*


class CompleteSignUp : AppCompatActivity() {
    private lateinit var back: ImageButton
    private lateinit var completebt: Button
    private lateinit var creaditcard_et: EditText
    private lateinit var cnameofcard_et: EditText
    private lateinit var monthcard: EditText
    private lateinit var yearcard: EditText
    private lateinit var cvvcard: EditText
    private lateinit var checkbox: CheckBox
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private var MONTH: Int = 0
    private var YEAR: Int = 0
    private lateinit var stripe : Stripe
    private lateinit var cardinput : CardInputWidget
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_sign_up)

        cardinput = findViewById(R.id.cardinput)
        findIds()
        listeners()
    }




    private fun listeners() {
        // com.stripe.Stripe.apiKey = Constants.STRIPEKEY
        back.setOnClickListener { onBackPressed() }
        completebt.setOnClickListener {

            val stripe1 = Stripe(this, Constants.STRIPEKEY!!)
            com.stripe.Stripe.apiKey = Constants.STRIPEKEY;
            val card: MutableMap<String, Any> = HashMap()
            card.put("number", "4000002500003155")
            card.put("exp_month", 11)
            card.put("exp_year", 2022)
            card.put("cvc", "251")
            val params: MutableMap<String, Any> = HashMap()
            params["type"] = "card"
            params["card"] = card


            var paymentMethod : PaymentMethod
            try {
                paymentMethod = PaymentMethod.create(params)
                Log.d("stoken", "" + paymentMethod.id)
            } catch (e: Exception)
            {
                Log.d("error", "" + e.cause)
            }

            val paymentIntent : PaymentIntent




            when {
                creaditcard_et.text.isEmpty() -> {
                    creaditcard_et.requestFocus()
                    creaditcard_et.setError("Enter Card")
                }

                cnameofcard_et.text.isEmpty() -> {
                    cnameofcard_et.requestFocus()
                    cnameofcard_et.setError("Enter name on card")
                }

                monthcard.text.isEmpty() -> {
                    monthcard.requestFocus()
                    monthcard.setError("Enter expiry month")
                }

                yearcard.text.isEmpty() -> {
                    yearcard.requestFocus()
                    yearcard.setError("Enter expiry year")
                }

                cvvcard.text.isEmpty() -> {
                    cvvcard.requestFocus()
                    cvvcard.setError("Enter cvv")
                }
                else -> {
                    pd.show()

                    val card2 = Card(
                        creaditcard_et.text.toString(),
                        MONTH,
                        YEAR,
                        cvvcard.text.toString()
                    )

                    val stripe1 = Stripe(this, Constants.STRIPEKEY)



                    stripe1.createToken(card2, object : TokenCallback {

                        override fun onError(error: java.lang.Exception) {
                            pd.dismiss()
                            Log.d("STRIPE_TOKEN", "" + error)
                        }

                        override fun onSuccess(token: com.stripe.android.model.Token?) {
                            pd.dismiss()
                            Log.d("STRIPE_TOKEN", "" + token)
                        }

                    })

                    // The synchronous way to do it (DON'T DO BOTH)


                }
            }

            // startActivity(Intent(this, Dashboard::class.java))

        }

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
        MONTH = separate1[1].toInt()
        YEAR = separate1[2].toInt()
        monthcard.setText(separate1[1])
        yearcard.setText(separate1[2])
    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)


        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorLightTheme))
        back = findViewById(R.id.back)
        completebt = findViewById(R.id.completebt)
        monthcard = findViewById(R.id.monthcard)
        yearcard = findViewById(R.id.yearcard)
        creaditcard_et = findViewById(R.id.creaditcard_et)
        cnameofcard_et = findViewById(R.id.cnameofcard_et)
        cvvcard = findViewById(R.id.cvvcard)
        checkbox = findViewById(R.id.checkbox)
    }
}


