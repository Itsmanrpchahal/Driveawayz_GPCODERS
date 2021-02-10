package com.driveawayz.SignUp

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.driveawayz.Constant.BaseClass
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.Dashboard
import retrofit2.Response

class SignUpDetail2 : BaseClass(),Controller.AddVehiclesAPI {

    private lateinit var back: ImageButton
    private lateinit var nextbt: Button
    private lateinit var make_et: EditText
    private lateinit var type_et: EditText
    private lateinit var year_et: EditText
    private lateinit var transsmisuion_et: EditText
    private lateinit var state_et: EditText
    private lateinit var licenceplate_et: EditText
    private lateinit var controller: Controller
    private lateinit var utility: Utility
    private lateinit var pd:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_detail2)
        controller = Controller()
        controller.Controller(this)

        findIds()
        listeners()
    }

    private fun listeners() {
        back.setOnClickListener { onBackPressed() }
        nextbt.setOnClickListener {

            when {
                make_et.text.isEmpty() -> {
                    make_et.requestFocus()
                    make_et.error = "Enter make"
                }

                type_et.text.isEmpty() -> {
                    type_et.requestFocus()
                    type_et.error = "Enter type"
                }

                year_et.text.isEmpty() -> {
                    year_et.requestFocus()
                    year_et.error = "Enter year"
                }

                transsmisuion_et.text.isEmpty() -> {
                    transsmisuion_et.requestFocus()
                    transsmisuion_et.error = "Enter transmission"
                }

                licenceplate_et.text.isEmpty() -> {
                    licenceplate_et.requestFocus()
                    licenceplate_et.error = "Enter licence plate"
                }

                state_et.text.isEmpty() -> {
                    state_et.requestFocus()
                    state_et.error = "Enter state"
                }
                else -> {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.AddVehicle("Bearer "+getStringVal(Constants.TOKEN)
                        ,make_et.text.toString()
                        ,type_et.text.toString()
                        ,year_et.text.toString()
                        ,transsmisuion_et.text.toString()
                        ,licenceplate_et.text.toString()
                        ,state_et.text.toString())

                }
            }


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
        back = findViewById(R.id.back)
        nextbt = findViewById(R.id.nextbt)
        make_et = findViewById(R.id.make_et)
        type_et = findViewById(R.id.type_et)
        year_et = findViewById(R.id.year_et)
        transsmisuion_et = findViewById(R.id.transsmisuion_et)
        state_et = findViewById(R.id.state_et)
        licenceplate_et = findViewById(R.id.licenceplate_et)
    }

    override fun onAddVehicleSuccess(success: Response<AddVehiclesResponse>) {

        pd.dismiss()
        if (success.isSuccessful)
        {
            if (success.code()==201)
            {
                startActivity(Intent(this, Dashboard::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                finish()
            } else {
                utility.relative_snackbar(
                    window.currentFocus,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
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