package com.driveawayz.OTPScreen

import `in`.aabhasjindal.otptextview.OtpTextView
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.driveawayz.Constant.BaseClass
import com.driveawayz.Controller.Controller
import com.driveawayz.OTPScreen.response.NumberVerifyResponse
import com.driveawayz.R
import com.driveawayz.SignUp.SignUpDetail1
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import retrofit2.Response


class OTPScreen : BaseClass(), Controller.SignUpPhoneAPI, Controller.VerifyPhoneAPI {

    private lateinit var mobileno: String
    private lateinit var code: String
    private lateinit var back: ImageButton
    private lateinit var resendotpbt: Button
    private lateinit var callme: Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var verifynow: Button
    private lateinit var otp_view: OtpTextView
    private lateinit var controller: Controller
    private lateinit var verificartionCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p_screen)
        findIds()
        listeners()
        startTimeCounter()
    }

    fun startTimeCounter() {

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                resendotpbt.setText("seconds remaining: " + millisUntilFinished / 1000)
                resendotpbt.setClickable(false)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                resendotpbt.setText("Didn’t receive otp,Resend OTP")
                callme.setTextColor(getColor(R.color.colorBlack))
                callme.isEnabled = true
                resendotpbt.setClickable(true)
            }
        }.start()
    }

    fun startTimeCounterCall() {

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                callme.setClickable(false)
                callme.setTextColor(getColor(R.color.colorGreyLight))
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                callme.setTextColor(getColor(R.color.colorBlack))
                callme.isEnabled = true
                resendotpbt.setClickable(true)
            }
        }.start()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }

        callme.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
//                when {
//                    otp_view.otp!!.length.equals(0) -> {
//                        otp_view.requestFocus()
//                        otp_view.showError()
//                    }
//                    else -> {
                startTimeCounterCall()
                controller.SignUpPhone(code + mobileno, "call")
                //  }
                //}
            } else {
                Toast.makeText(this, getString(R.string.nointernet), Toast.LENGTH_SHORT).show()
            }
        }

        resendotpbt.setOnClickListener {
            when {
                resendotpbt.text.equals("Didn’t receive otp,Resend OTP") -> {


                    if (utility.isConnectingToInternet(this)) {
                        startTimeCounter()
                        controller.SignUpPhone(code + mobileno, "sms")
                    } else {
                        Toast.makeText(this, getString(R.string.nointernet), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        verifynow.setOnClickListener {
            if (utility.isConnectingToInternet(this)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                when {
                    otp_view.otp!!.length.equals(0) -> {
                        otp_view.requestFocus()
                        otp_view.showError()
                    }
                    else -> {
                        controller.VerifyPhone(code + mobileno, otp_view.otp.toString())
                    }
                }
            }
        }
    }

    private fun findIds() {
        mobileno = intent.getStringExtra(Constants.MOBILENUMBER)!!
        code = intent.getStringExtra(Constants.CODE)!!
        utility = Utility()
        controller = Controller()
        controller.Controller(this, this)
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
        resendotpbt = findViewById(R.id.resendotpbt)
        callme = findViewById(R.id.callme)
        verifynow = findViewById(R.id.verifynow)
        otp_view = findViewById(R.id.otp_view)
    }

    override fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {

            Toast.makeText(this, "OTP Sent", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "" + success.message(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onVerifyPhoneSuccess(success: Response<NumberVerifyResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (!success.body()!!.status.equals("pending")) {
                startActivity(Intent(this, SignUpDetail1::class.java))
            } else {
                otp_view.requestFocus()
                otp_view.showError()
            }
        } else {
            Toast.makeText(this, success.message(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show()
    }
}