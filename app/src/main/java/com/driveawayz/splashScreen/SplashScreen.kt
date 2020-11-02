package com.driveawayz.splashScreen

import android.content.Intent
import android.graphics.Color
import com.driveawayz.MainActivity
import com.driveawayz.R
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.driveawayz.Constant.BaseClass
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest

class SplashScreen : BaseClass() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        methodRequiresPermission()
    }

    private fun methodRequiresPermission() = runWithPermissions(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ){
        Handler().postDelayed({

            startActivity(
                Intent(this,
                MainActivity::class.java)
            )
            finish()

        },4000)
    }

    private val quickPermissionsOptions = QuickPermissionsOptions(
        rationaleMessage = "Custom  rational messsage",
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = { rationalCallback(it)}
    )

    private fun rationalCallback(request: QuickPermissionsRequest)
    {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("This is the custom rational dialog. Please allow us to procees "+ "asking for permissions again, or cancel to end the permission flow.")
            .setPositiveButton("Go Ahead"){_,_ -> request.proceed()}
            .setNegativeButton("Cancel"){_,_ -> request.cancel()}
            .setCancelable(false)
            .show()
    }
}