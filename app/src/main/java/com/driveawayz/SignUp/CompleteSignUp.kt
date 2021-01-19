package com.driveawayz.SignUp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.renderscript.ScriptIntrinsicConvolve3x3.create
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
import com.google.gson.JsonObject
import com.stripe.android.Stripe
import com.stripe.android.model.*
import com.stripe.stripeterminal.SessionTokenManager_Factory.create
import com.stripe.stripeterminal.model.external.PaymentMethod
import org.json.JSONObject
import java.net.URI.create
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
    private lateinit  var paymentMethod : com.stripe.android.model.PaymentMethod
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_sign_up)

        findIds()
        listeners()
    }

    @SuppressLint("LogNotTimber")
    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        completebt.setOnClickListener {

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
//                    val card = Card(
//                        creaditcard_et.text.toString(),
//                        MONTH,
//                        YEAR,
//                        cvvcard.text.toString()
//                    )
//                    val card2 = Card(
//                        creaditcard_et.text.toString(),
//                        MONTH,
//                        YEAR,
//                        cvvcard.text.toString()
//                    )
//                    val stripe1 = Stripe(this, Constants.STRIPEKEY)
//                    stripe1.createToken(card2, object : TokenCallback {
//
//                        override fun onError(error: java.lang.Exception) {
//                            pd.dismiss()
//                            Log.d("STRIPE_TOKEN",""+error)
//                        }
//
//                        override fun onSuccess(token: Token) {
//                            pd.dismiss()
//                            Log.d("STRIPE_TOKEN",""+token)
//                        }
//                    })
                    val stripe = Stripe(
                        this,
                        Constants.STRIPEKEY!!
                    )
                    // The synchronous way to do it (DON'T DO BOTH)

                  //  val cardSourceParams = SourceParams.createCardParams(card)
// The asynchronous way to do it. Call this method on the main thread.
//                    stripe.createSource(
//                        cardSourceParams,
//                        callback = object : ApiResultCallback<Source> {
//                            override fun onSuccess(source: Source) {
//                               Log.d("card",""+source)
//                            }
//
//                            override fun onError(error: Exception) {
//                                Log.d("errror",""+error)
//                            }
//                        }
//                    )




                    val card: MutableMap<String, Any> = HashMap()
                    card["number"] = "4242424242424242"
                    card["exp_month"] = 1
                    card["exp_year"] = 2022
                    card["cvc"] = "314"
                    val params: MutableMap<String, Any> = HashMap()
                    params["type"] = "card"
                    params["card"] = card


                    val id :String? = null
                    //id = paymentMethod.id.toString()
                    paymentMethod = PaymentMethod.create(params)
                    Log.d("data", "" + paymentMethod.id.toString())
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
        creaditcard_et = findViewById(R.id.creaditcard_et)
        cnameofcard_et = findViewById(R.id.cnameofcard_et)
        cvvcard = findViewById(R.id.cvvcard)
        checkbox = findViewById(R.id.checkbox)
    }
}