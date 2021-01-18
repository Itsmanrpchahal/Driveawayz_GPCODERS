package com.driveawayz.splashScreen

import android.content.Intent
import com.driveawayz.MainActivity
import com.driveawayz.R
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.driveawayz.Constant.BaseClass
import com.driveawayz.Controller.Controller
import com.driveawayz.SignUp.CompleteSignUp
import com.driveawayz.SignUp.SignUpDetail1
import com.driveawayz.SignUp.SignUpDetail2
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.Dashboard
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest
import retrofit2.Response

class SplashScreen : BaseClass(), Controller.MeAPI {
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        utility = Utility()
        controller = Controller()
        controller.Controller(this)
        if (!getStringVal(Constants.TOKEN).equals("")) {
            controller.Me(getStringVal(Constants.TOKEN)!!)
        } else {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }



        Log.d("token", "" + getStringVal(Constants.TOKEN))
    }

    private fun methodRequiresPermission(success: Response<MeResponse>) = runWithPermissions(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) {
        Handler().postDelayed({

            if (!getStringVal(Constants.TOKEN).equals("")) {
                if (getStringVal(Constants.ADDRESS).equals("0")) {
                    startActivity(Intent(this, SignUpDetail1::class.java))
                } else if (getStringVal(Constants.VEHICLES).equals("0")) {
                    startActivity(Intent(this, SignUpDetail2::class.java))
                } else if (getStringVal(Constants.CARDS).equals("0")) {
                    startActivity(Intent(this, CompleteSignUp::class.java))
                } else {
                    startActivity(Intent(this, Dashboard::class.java))
                }

            } else {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }

            finish()

        }, 2000)
    }

    private val quickPermissionsOptions = QuickPermissionsOptions(
        rationaleMessage = "Custom  rational messsage",
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = { rationalCallback(it) }
    )

    private fun rationalCallback(request: QuickPermissionsRequest) {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("This is the custom rational dialog. Please allow us to procees " + "asking for permissions again, or cancel to end the permission flow.")
            .setPositiveButton("Go Ahead") { _, _ -> request.proceed() }
            .setNegativeButton("Cancel") { _, _ -> request.cancel() }
            .setCancelable(false)
            .show()
    }

    override fun onMeSuccess(success: Response<MeResponse>) {
        Log.d("success", "" + success.body()?.address)

        methodRequiresPermission(success)
        setStringVal(Constants.ADDRESS, success.body()?.address?.size.toString())
        setStringVal(Constants.VEHICLES, success.body()?.vehicle?.size.toString())
        setStringVal(Constants.CARDS, success.body()?.cards?.size.toString())


    }

    override fun onError(error: String) {
        Log.d("errro", "" + error)
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show()
    }
}