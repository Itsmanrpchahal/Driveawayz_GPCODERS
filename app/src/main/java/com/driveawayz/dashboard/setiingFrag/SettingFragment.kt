package com.driveawayz.dashboard.setiingFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.R

class SettingFragment : BaseFrag() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_setting, container, false)
        return  view
    }

}