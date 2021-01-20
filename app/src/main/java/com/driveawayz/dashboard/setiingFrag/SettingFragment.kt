package com.driveawayz.dashboard.setiingFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingFragment : BaseFrag() {

    private lateinit var bottomnavigaton : BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_setting, container, false)

        findIds(view)
        listeners()
        return  view
    }

    private fun listeners() {
        bottomnavigaton.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when  {
                    item.itemId == R.id.nav_profile -> {
                        Toast.makeText(context,"Profile",Toast.LENGTH_SHORT).show()
                    }

                    item.itemId == R.id.nav_address -> {
                        Toast.makeText(context,"Address",Toast.LENGTH_SHORT).show()
                    }

                    item.itemId == R.id.nav_MyVehicles -> {
                        Toast.makeText(context,"Vehicles",Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

        })
    }

    private fun findIds(view: View?) {
        bottomnavigaton = view!!.findViewById(R.id.bottomnavigaton)
    }

}