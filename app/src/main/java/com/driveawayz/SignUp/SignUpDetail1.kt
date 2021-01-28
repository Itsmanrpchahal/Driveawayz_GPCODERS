package com.driveawayz.SignUp

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import com.driveawayz.Constant.BaseClass
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.SignUp.signupphone.response.SignUp1User
import com.driveawayz.SignUp.signupphone.response.AddNewAddressResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class SignUpDetail1 : BaseClass(), Controller.SignUp1API,Controller.AddNewAddress {

    private lateinit var name_et: EditText
    private lateinit var email_et: EditText
    private lateinit var cpass_et : EditText
    private lateinit var pass_et: EditText
    private lateinit var street_et: EditText
    private lateinit var address_et: EditText
    private lateinit var back: ImageButton
    private lateinit var nextbt: Button
    private lateinit var dob_et: EditText
    private lateinit var datetime: String
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    val c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail1)
        controller = Controller()
        controller.Controller(this, this)
        findIds()
        lisenters()
        if (!getStringVal(Constants.NAME).equals(""))
        {
            name_et.setText(getStringVal(Constants.NAME))
            name_et.isEnabled = false
            email_et.setText(getStringVal(Constants.EMAIL))
            email_et.isEnabled = false
            pass_et.isEnabled = false
            pass_et.setText("*******")
            dob_et.isEnabled = false
            dob_et.setText(getStringVal(Constants.DOB))

        }
    }

    private fun lisenters() {

        back.setOnClickListener { onBackPressed()}
        nextbt.setOnClickListener {
            when {
                name_et.text.isEmpty() -> {
                    name_et.requestFocus()
                    name_et.error = "Enter Name"
                }

                email_et.text.isEmpty() -> {
                    email_et.requestFocus()
                    email_et.error = "Enter Email"
                }

                pass_et.text.isEmpty() -> {
                    pass_et.requestFocus()
                    pass_et.error = "Enter Password"
                }

                cpass_et.text.isEmpty() -> {
                    cpass_et.requestFocus()
                    cpass_et.error = "Enter confirm password"
                }

                !pass_et.text.toString().equals(cpass_et.text.toString()) ->
                {
                    cpass_et.requestFocus()
                    cpass_et.error = "Password not matched"
                }

                dob_et.text.isEmpty() -> {
                    dob_et.requestFocus()
                    dob_et.error = "Enter date of birth"
                }

                street_et.text.isEmpty() -> {
                    street_et.requestFocus()
                    street_et.error = "Enter Street"
                }

                address_et.text.isEmpty() -> {
                    address_et.requestFocus()
                    address_et.error = "Enter Address"
                }
                else -> {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    if (getStringVal(Constants.PHONENUMBERVERIFIED).equals("true"))
                    {
                        pd.show()
                        controller.AddNewAddress(
                            "Bearer " +getStringVal(Constants.TOKEN),
                            street_et.text.toString(),
                            address_et.text.toString()
                        )
                    } else {
                        pd.show()
                        controller.SignUp1(
                            name_et.text.toString(),
                            email_et.text.toString(),
                            pass_et.text.toString(),
                            datetime,
                            getStringVal(
                                Constants.CODE
                            ) + getStringVal(Constants.MOBILENUMBER),
                            true
                        )
                    }
                }
            }
        }

        var dateSetListener = object : DatePickerDialog.OnDateSetListener {
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
            val datePickerDialog = DatePickerDialog(
                this,
                R.style.DialogTheme,
                dateSetListener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis() + 1000
            datePickerDialog.show()
        }

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


        // create an OnDateSetListener

        back = findViewById(R.id.back)
        nextbt = findViewById(R.id.nextbt)
        dob_et = findViewById(R.id.dob_et)
        name_et = findViewById(R.id.name_et)
        cpass_et = findViewById(R.id.cpass_et)
        email_et = findViewById(R.id.email_et)
        pass_et = findViewById(R.id.pass_et)
        street_et = findViewById(R.id.street_et)
        address_et = findViewById(R.id.address_et)
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1, Locale.UK)
        datetime = sdf1.format(c.time)
        Log.d("TIME", "" + datetime)
        dob_et.setText(sdf.format(c.time))
    }


    override fun onSignUpSuccess(success: Response<SignUp1User>) {
        //pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==201)
            {
                setStringVal(Constants.TOKEN, success.body()?.accessToken)
                controller.AddNewAddress(
                    "Bearer " + success.body()?.accessToken,
                    street_et.text.toString(),
                    address_et.text.toString()
                )
            } else if (success.code()==400)
            {
                pd.dismiss()
                email_et.requestFocus()
                email_et.setError("Email already exist")
                utility.relative_snackbar(
                    window.currentFocus,
                    "Email already exist",
                    getString(R.string.close_up)
                )
            }

        } else {
            pd.dismiss()
            utility.relative_snackbar(
                window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }


    }

    override fun onUpdateAddress(success: Response<AddNewAddressResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            startActivity(
                Intent(this, SignUpDetail2::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
            )
            finish()
        } else {
            utility.relative_snackbar(
                window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(
            window.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }

}