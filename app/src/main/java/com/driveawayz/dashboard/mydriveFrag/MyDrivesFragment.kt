package com.driveawayz.dashboard.mydriveFrag

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.driveawayz.R
import com.driveawayz.Utilities.Utility

class MyDrivesFragment : Fragment() {

    private lateinit var future_bt: Button
    private lateinit var past_bt: Button
    private lateinit var utility: Utility

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_my_drives, container, false)

        findIds(view)
        listeners()

        return view
    }

    private fun listeners() {
        future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
        future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
        past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
        past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));

        future_bt.setOnClickListener {

            if (utility.isConnectingToInternet(context)) {
                future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
                future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
                past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
                past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));
            } else {
                context?.registerReceiver(
                    broadcastReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }

        past_bt.setOnClickListener {

            if (utility.isConnectingToInternet(context)) {
                future_bt.setBackground(getResources().getDrawable(R.drawable.leftblackborder));
                past_bt.setBackground(getResources().getDrawable(R.drawable.rightfillblack));
                past_bt.setTextColor(getResources().getColor(R.color.colorWhite));
                future_bt.setTextColor(getResources().getColor(R.color.colorBlack));
            } else {
                context?.registerReceiver(
                    broadcastReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }


        }
    }

    private fun findIds(view: View?) {
        future_bt = view?.findViewById(R.id.future_bt)!!
        past_bt = view?.findViewById(R.id.past_bt)
        utility = Utility()
    }

    //Check Internet Connection
    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val notConnected =
                p1!!.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)

            if (notConnected) {
                Utility.noConnectionDialog(context, "1")
            } else {
                Utility.noConnectionDialog(context, "0")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        context?.registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

}