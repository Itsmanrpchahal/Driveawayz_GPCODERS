package com.driveawayz.SignUp.signupphone

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.driveawayz.Controller.Controller
import com.driveawayz.Login.LoginScreen
import com.driveawayz.OTPScreen.OTPScreen
import com.driveawayz.R
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.rilixtech.widget.countrycodepicker.Country
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import retrofit2.Response

@Suppress("DEPRECATION")
class SignUpPhoneNo : AppCompatActivity(), CountryCodePicker.OnCountryChangeListener,
    Controller.SignUpPhoneAPI {
    private lateinit var nextBt: Button
    private lateinit var ccp: CountryCodePicker
    lateinit var controller: Controller
    private lateinit var back: ImageButton
    private lateinit var phone_number_et: EditText
    private lateinit var mySpinner : Spinner
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var alreadyhaveaccount_bt : Button
    private  var c_code: String = "+91"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_phone_no)

        findIDs()
       listeners()
    }

    private fun listeners() {
        nextBt.setOnClickListener {

            when {
                phone_number_et.text.isEmpty() -> {
                    phone_number_et.requestFocus()
                    phone_number_et.setError("Enter mobile number")
                }
                else -> {

                    if (phone_number_et.text.length<7 || phone_number_et.text.length>13)
                    {
                       phone_number_et.requestFocus()
                        phone_number_et.setError("Invalid phone number")
                    } else {
                        if (utility.isConnectingToInternet(this)) {
                          closeKeyBoard()
                            pd.show()
                            pd.setContentView(R.layout.loading)
                            controller.SignUpPhone(c_code + phone_number_et.text.toString(), "sms")
                        } else {
                            pd!!.dismiss()
                            Toast.makeText(
                                this,
                                "" + getString(R.string.nointernet),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }


        }

        back.setOnClickListener { onBackPressed() }

        alreadyhaveaccount_bt.setOnClickListener { startActivity(Intent(this,LoginScreen::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)) }
    }

    private fun findIDs() {
        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        nextBt = findViewById(R.id.nextBt)
        ccp = findViewById(R.id.ccp)
        back = findViewById(R.id.back)
        phone_number_et = findViewById(R.id.phone_number_et)
        alreadyhaveaccount_bt = findViewById(R.id.alreadyhaveaccount_bt)
        ccp!!.setOnCountryChangeListener(this)
    }


    override fun onCountrySelected(selectedCountry: Country?) {
        c_code = selectedCountry!!.phoneCode
    }

    override fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>) {
        pd!!.dismiss()
        if (success.isSuccessful) {
            startActivity(Intent(this, OTPScreen::class.java).putExtra(Constants.MOBILENUMBER, phone_number_et.text.toString()).putExtra(Constants.CODE,c_code))
        }else {
            Toast.makeText(this,success.message(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(error: String) {
        pd!!.dismiss()
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}