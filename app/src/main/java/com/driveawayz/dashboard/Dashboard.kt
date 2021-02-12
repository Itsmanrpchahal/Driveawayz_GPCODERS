package com.driveawayz.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.driveawayz.Constant.BaseClass
import com.driveawayz.MainActivity
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.homeFrag.PickUpPoint
import com.driveawayz.dashboard.mydriveFrag.MyDrivesFragment
import com.driveawayz.dashboard.notificationFrag.NotificationFragment
import com.driveawayz.dashboard.paymentFrag.PaymentFragment
import com.driveawayz.dashboard.setiingFrag.SettingFragment
import com.driveawayz.dashboard.supportFrag.SupportFragment
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.maindrawer.*
import org.w3c.dom.Text

class Dashboard : BaseClass(), View.OnClickListener {

    private lateinit var frameLayout: NavController
    private lateinit var drawerLayout: DrawerLayout
    lateinit var appbarmain: AppBarLayout
    private lateinit var menu: ImageButton
    private lateinit var home_nav : Button
    private lateinit var mydrives_nav : Button
    private lateinit var paymentmethod_nav : Button
    private lateinit var notifications_nav : Button
    private lateinit var setting_nav : Button
    private lateinit var support_nav : Button
    private lateinit var logout_nav : Button
    private lateinit var utility: Utility
    private lateinit var fragmentManager : FragmentManager
    private lateinit var title : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        fragmentManager = this.supportFragmentManager
        findIds()
        listeners()
    }

    private fun listeners() {
        menu.setOnClickListener(this)
        home_nav.setOnClickListener(this)
        notifications_nav.setOnClickListener(this)
        mydrives_nav.setOnClickListener(this)
        paymentmethod_nav.setOnClickListener(this)
        setting_nav.setOnClickListener(this)
        support_nav.setOnClickListener(this)
        logout_nav.setOnClickListener(this)

    }


    // Close or open drawer menu
    private fun openCloseDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            drawerLayout.openDrawer(Gravity.LEFT)
        }
    }


    private fun findIds() {
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorTheme))
        utility = Utility()


        drawerLayout = findViewById(R.id.drawer_layout)
        appbarmain = findViewById(R.id.appbarmain)
        menu = findViewById(R.id.menu)
        home_nav = findViewById(R.id.home_nav)
        notifications_nav = findViewById(R.id.notifications_nav)
        mydrives_nav = findViewById(R.id.mydrives_nav)
        paymentmethod_nav = findViewById(R.id.paymentmethod_nav)
        setting_nav = findViewById(R.id.setting_nav)
        support_nav = findViewById(R.id.support_nav)
        logout_nav = findViewById(R.id.logout_nav)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = findViewById(R.id.title)

        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,PickUpPoint()).commit()
        title.setText("Home")

        home_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
        home_nav.setTextColor(resources.getColor(R.color.white))
        home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_white,0,0,0)

        notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
        notifications_nav.setTextColor(resources.getColor(R.color.black))
        notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

        mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
        mydrives_nav.setTextColor(resources.getColor(R.color.black))
        mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

        paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
        paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
        paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

        setting_nav.setBackgroundColor(resources.getColor(R.color.white))
        setting_nav.setTextColor(resources.getColor(R.color.black))
        setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

        support_nav.setBackgroundColor(resources.getColor(R.color.white))
        support_nav.setTextColor(resources.getColor(R.color.black))
        support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
    }


    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        home_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
        home_nav.setTextColor(resources.getColor(R.color.white))
        home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_white,0,0,0)

        notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
        notifications_nav.setTextColor(resources.getColor(R.color.black))
        notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

        mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
        mydrives_nav.setTextColor(resources.getColor(R.color.black))
        mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

        paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
        paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
        paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

        setting_nav.setBackgroundColor(resources.getColor(R.color.white))
        setting_nav.setTextColor(resources.getColor(R.color.black))
        setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

        support_nav.setBackgroundColor(resources.getColor(R.color.white))
        support_nav.setTextColor(resources.getColor(R.color.black))
        support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
        title.setText("Home")
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.menu -> {
                openCloseDrawer()
            }

            R.id.home_nav -> {
                openCloseDrawer()
                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,PickUpPoint()).addToBackStack(null).commit()
                title.setText("Home")

                home_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                home_nav.setTextColor(resources.getColor(R.color.white))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_white,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
                notifications_nav.setTextColor(resources.getColor(R.color.black))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
                mydrives_nav.setTextColor(resources.getColor(R.color.black))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.white))
                setting_nav.setTextColor(resources.getColor(R.color.black))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.white))
                support_nav.setTextColor(resources.getColor(R.color.black))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)

            }

            R.id.notifications_nav -> {
                openCloseDrawer()
                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,NotificationFragment()).addToBackStack(null).commit()
                title.setText("Notifications")

                home_nav.setBackgroundColor(resources.getColor(R.color.white))
                home_nav.setTextColor(resources.getColor(R.color.black))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_black,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                notifications_nav.setTextColor(resources.getColor(R.color.white))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_white,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
                mydrives_nav.setTextColor(resources.getColor(R.color.black))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.white))
                setting_nav.setTextColor(resources.getColor(R.color.black))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.white))
                support_nav.setTextColor(resources.getColor(R.color.black))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
            }

            R.id.mydrives_nav -> {
                openCloseDrawer()
                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,MyDrivesFragment()).addToBackStack(null).commit()
                title.setText("MyDrives")

                home_nav.setBackgroundColor(resources.getColor(R.color.white))
                home_nav.setTextColor(resources.getColor(R.color.black))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_black,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
                notifications_nav.setTextColor(resources.getColor(R.color.black))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                mydrives_nav.setTextColor(resources.getColor(R.color.white))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydives_white,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.white))
                setting_nav.setTextColor(resources.getColor(R.color.black))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.white))
                support_nav.setTextColor(resources.getColor(R.color.black))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
            }

            R.id.paymentmethod_nav -> {
                openCloseDrawer()
                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,PaymentFragment()).addToBackStack(null).commit()
                title.setText("Payment Methods")

                home_nav.setBackgroundColor(resources.getColor(R.color.white))
                home_nav.setTextColor(resources.getColor(R.color.black))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_black,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
                notifications_nav.setTextColor(resources.getColor(R.color.black))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
                mydrives_nav.setTextColor(resources.getColor(R.color.black))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.white))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.payment_white,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.white))
                setting_nav.setTextColor(resources.getColor(R.color.black))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.white))
                support_nav.setTextColor(resources.getColor(R.color.black))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
            }

            R.id.setting_nav -> {
                openCloseDrawer()
                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,SettingFragment()).addToBackStack(null).commit()
                title.setText("Setting")

                home_nav.setBackgroundColor(resources.getColor(R.color.white))
                home_nav.setTextColor(resources.getColor(R.color.black))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_black,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
                notifications_nav.setTextColor(resources.getColor(R.color.black))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
                mydrives_nav.setTextColor(resources.getColor(R.color.black))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                setting_nav.setTextColor(resources.getColor(R.color.white))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_white,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.white))
                support_nav.setTextColor(resources.getColor(R.color.black))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_black,0,0,0)
            }

            R.id.support_nav -> {
                openCloseDrawer()

                fragmentManager.popBackStack()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,SupportFragment()).addToBackStack(null).commit()
                title.setText("Support")

                home_nav.setBackgroundColor(resources.getColor(R.color.white))
                home_nav.setTextColor(resources.getColor(R.color.black))
                home_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_black,0,0,0)

                notifications_nav.setBackgroundColor(resources.getColor(R.color.white))
                notifications_nav.setTextColor(resources.getColor(R.color.black))
                notifications_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.notification_black,0,0,0)

                mydrives_nav.setBackgroundColor(resources.getColor(R.color.white))
                mydrives_nav.setTextColor(resources.getColor(R.color.black))
                mydrives_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mydrives_black,0,0,0)

                paymentmethod_nav.setBackgroundColor(resources.getColor(R.color.white))
                paymentmethod_nav.setTextColor(resources.getColor(R.color.black))
                paymentmethod_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.paymenyt_black,0,0,0)

                setting_nav.setBackgroundColor(resources.getColor(R.color.white))
                setting_nav.setTextColor(resources.getColor(R.color.black))
                setting_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_black,0,0,0)

                support_nav.setBackgroundColor(resources.getColor(R.color.colorTheme))
                support_nav.setTextColor(resources.getColor(R.color.white))
                support_nav.setCompoundDrawablesWithIntrinsicBounds(R.drawable.support_white,0,0,0)
            }

            R.id.logout_nav -> {
                clearStringVal(Constants.TOKEN)
                startActivity(Intent(this,MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }

        }
    }

}