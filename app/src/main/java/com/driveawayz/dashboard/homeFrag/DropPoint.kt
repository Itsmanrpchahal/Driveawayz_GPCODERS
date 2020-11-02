package com.driveawayz.dashboard.homeFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.driveawayz.R

class DropPoint : Fragment() {

    private lateinit var setDestination_bt: Button
    private lateinit var manager: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_drop_point, container, false)
        manager = fragmentManager!!
        findIds(view)
        listeners()
        return view

    }

    private fun listeners() {
        setDestination_bt.setOnClickListener {
            manager.beginTransaction().replace(R.id.nav_host_fragment,BookDriver()).addToBackStack(null).commit()
        }
    }

    private fun findIds(view: View?) {
        setDestination_bt = view?.findViewById(R.id.setDestination_bt)!!

    }

}
