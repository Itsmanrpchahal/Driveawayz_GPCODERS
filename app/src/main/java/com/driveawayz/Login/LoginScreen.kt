package com.driveawayz.Login

import android.app.Dialog
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import com.driveawayz.Constant.BaseClass
import com.driveawayz.Controller.Controller
import com.driveawayz.Login.response.ForgotResponse
import com.driveawayz.Login.response.LoginResponse
import com.driveawayz.R
import com.driveawayz.SignUp.SignUpDetail1
import com.driveawayz.SignUp.SignUpDetail2
import com.driveawayz.SignUp.signupphone.SignUpPhoneNo
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.Dashboard
import com.driveawayz.splashScreen.MeResponse
import retrofit2.Response


@Suppress("DEPRECATION")
class LoginScreen : BaseClass(), Controller.LoginAPI, Controller.MeAPI ,Controller.ForgotPasswordAPI{

    private lateinit var back: ImageButton
    private lateinit var email_et: EditText
    private lateinit var pass_et: EditText
    private lateinit var signInbt: Button
    private lateinit var donthaveaccountbt: Button
    private lateinit var loading: RelativeLayout
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var labeled: RelativeLayout
    private lateinit var controller: Controller
    private lateinit var notC : String
    private lateinit var forgotpassword_tv : TextView
    private lateinit var forgotDialog : Dialog

    private lateinit var f_email_et : EditText
    private lateinit var reset_bt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        findIds()
        lisenters()
    }

    //Check Internet Connection
    private var broadcastReceiver : BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val notConnected = p1!!.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false)

            if (notConnected)
            {
                Utility.noConnectionDialog(this@LoginScreen,"1")
            } else {
                Utility.noConnectionDialog(this@LoginScreen,"0")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }


    private fun lisenters() {
        back.setOnClickListener { onBackPressed() }
        signInbt.setOnClickListener {

            when {
                email_et.text.isEmpty() -> {
                    email_et.requestFocus()
                    email_et.setError("Enter email")

                }
                pass_et.text.isEmpty() -> {
                    pass_et.requestFocus()
                    pass_et.setError("Enter password")
                }
                else -> {

                    if (utility.isConnectingToInternet(this)) {
                        hideKeyboard()
                        pd.show()
                        pd.setContentView(R.layout.loading)

                        controller.Login(email_et.text.toString(), pass_et.text.toString())

                    } else {
                        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
                    }

                    // startActivity(Intent(this, Dashboard::class.java))
                }
            }

        }
        donthaveaccountbt.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SignUpPhoneNo::class.java
                )
            )
        }

        forgotpassword_tv.setOnClickListener {
            forgot_Dialog()
        }
    }

    private fun forgot_Dialog() {
        forgotDialog = Dialog(this!!)
        val window: Window = forgotDialog.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        forgotDialog.setContentView(R.layout.forgotpassword)
        forgotDialog.setCancelable(true)
        forgotDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        f_email_et = forgotDialog.findViewById(R.id.email_et)
        reset_bt = forgotDialog.findViewById(R.id.reset_bt)

        reset_bt.setOnClickListener {
            when {
                f_email_et.text.isEmpty() -> {
                    f_email_et.requestFocus()
                    f_email_et.error = "Enter Email"
                } else -> {
                hideKeyboard()
                    pd.show()
                pd.setContentView(R.layout.loading)
                controller.ForgotPassword(f_email_et.text.toString())
                }
            }
        }


        forgotDialog.show()

    }

    private fun findIds() {
        utility = Utility()
        pd = ProgressDialog(this)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        controller.Controller(this,this,this)
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorLightTheme))
        back = findViewById(R.id.back)
        signInbt = findViewById(R.id.signInbt)
        donthaveaccountbt = findViewById(R.id.donthaveaccountbt)
        labeled = findViewById(R.id.labeled)
        pass_et = findViewById(R.id.pass_et)
        email_et = findViewById(R.id.email_et)
        forgotpassword_tv = findViewById(R.id.forgotpassword_tv)

    }

    override fun oLoginSucceess(success: Response<LoginResponse>) {
        if (success.isSuccessful) {

            if (success.code() == 201) {
                setStringVal(Constants.TOKEN, success.body()?.access_token)
                controller.Me("Bearer "+success.body()?.access_token!!)

            } else if (success.code() == 401) {
                utility.relative_snackbar(
                    window.currentFocus,
                    getString(R.string.entercorrect),
                    getString(R.string.close_up)
                )
            }
        } else {
            pd.dismiss()
            utility.relative_snackbar(
                window.currentFocus,
                getString(R.string.entercorrect),
                getString(R.string.close_up)
            )
        }
    }

    override fun onMeSuccess(success: Response<MeResponse>) {
        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==200)
            {
                setStringVal(Constants.NAME,success.body()?.name)
                setStringVal(Constants.EMAIL,success.body()?.email)
                setStringVal(Constants.DOB,success.body()?.dateOfBirth)
                setStringVal(Constants.MOBILENUMBER,success.body()?.phoneNumber)
                setStringVal(Constants.PHONENUMBERVERIFIED,
                    success.body()?.phoneVerified.toString()
                )

                if (success.body()?.address?.size==0) {
                    startActivity(
                        Intent(
                            this,
                            SignUpDetail1::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )

                    finish()

                } else if (success.body()?.vehicle?.size==0) {
                    startActivity(
                        Intent(
                            this,
                            SignUpDetail2::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    finish()
                }
//                else if (success.body()?.cards?.size==0) {
//                    startActivity(
//                        Intent(
//                            this,
//                            CompleteSignUp::class.java
//                        ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    )
//                    finish()
//                }
                else {
                    startActivity(Intent(this,
                        Dashboard::class.java).setFlags
                        (Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                    finish()
                }
            } else{
                utility.relative_snackbar(window.currentFocus,getString(R.string.nointernet),getString(R.string.close_up))
            }
        }

    }

    override fun onForgotPasswordAPI(success: Response<ForgotResponse>) {
            pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.body()?.error==false)
            {
                forgotDialog.dismiss()
                utility.relative_snackbar(window.currentFocus, success.body()?.message, getString(R.string.close_up))
            } else if (success.body()?.error==true) {
                f_email_et.requestFocus()
                f_email_et.error = success.body()!!.message
            }
        }else {
            utility.relative_snackbar(window.currentFocus, success.message(), getString(R.string.close_up))
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility.relative_snackbar(window.currentFocus, error, getString(R.string.close_up))
    }

    private fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

}