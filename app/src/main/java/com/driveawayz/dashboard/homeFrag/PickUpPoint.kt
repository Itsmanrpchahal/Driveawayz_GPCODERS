package com.driveawayz.dashboard.homeFrag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.driveawayz.R

class PickUpPoint : Fragment() {

    lateinit var manager : FragmentManager
    lateinit var setPickUpBt : Button
    lateinit var pickupEt : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_home, container, false)
        manager = getActivity()?.getSupportFragmentManager()!!;
        findIds(view)
        listeners()
        return view
    }

    private fun listeners() {
        setPickUpBt.setOnClickListener { manager.beginTransaction().replace(R.id.nav_host_fragment,DropPoint()).addToBackStack(null).commit() }
    }

    private fun findIds(view: View?) {
        setPickUpBt = view!!.findViewById(R.id.setPickUp_bt)
    }
}