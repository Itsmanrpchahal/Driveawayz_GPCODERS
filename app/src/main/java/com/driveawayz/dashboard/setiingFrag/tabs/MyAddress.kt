package com.driveawayz.dashboard.setiingFrag.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.driveawayz.R


class MyAddress : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_my_address, container, false)

        return view
    }

}