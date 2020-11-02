package com.driveawayz.dashboard.mydriveFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.driveawayz.R

class MyDrivesFragment : Fragment() {

    private lateinit var future_bt: Button
    private lateinit var past_bt: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View
        view = inflater.inflate(R.layout.fragment_my_drives, container, false)

        findIds(view)
        listeners()

        return  view
    }

    private fun listeners() {
        future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
        future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
        past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
        past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));

        future_bt.setOnClickListener {
            future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
            future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
            past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
            past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));
        }

        past_bt.setOnClickListener {
            future_bt.setBackground(getResources().getDrawable(R.drawable.leftblackborder));
            past_bt.setBackground(getResources().getDrawable(R.drawable.rightfillblack));
            past_bt.setTextColor(getResources().getColor(R.color.colorWhite));
            future_bt.setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    private fun findIds(view: View?) {
        future_bt = view?.findViewById(R.id.future_bt)!!
        past_bt = view?.findViewById(R.id.past_bt)
    }

}