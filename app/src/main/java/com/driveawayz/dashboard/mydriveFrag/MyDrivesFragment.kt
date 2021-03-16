package com.driveawayz.dashboard.mydriveFrag

import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.mydriveFrag.IF.DeleteRideID_IF
import com.driveawayz.dashboard.mydriveFrag.adater.MyDrivesAdapter
import com.driveawayz.dashboard.setiingFrag.adatper.MyVehicelAdapter
import com.driveawayz.splashScreen.MeResponse
import retrofit2.Response

class MyDrivesFragment : BaseFrag() ,Controller.MyDrivesAPI,Controller.DeleteRideAPI, DeleteRideID_IF {

    private lateinit var future_bt: Button
    private lateinit var past_bt: Button
    private lateinit var utility: Utility
    private lateinit var controller : Controller
    private lateinit var drives_recyler : RecyclerView
    private lateinit var pd: ProgressDialog
    private lateinit var futureRides : ArrayList<MyDrivesResponse.FutureRide>
    protected lateinit var pastRides : ArrayList<MyDrivesResponse.CompletedRide>
    lateinit var type : String
    lateinit var myDrivesAdapter : MyDrivesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_my_drives, container, false)
        deleterideidIf = this
        findIds(view)
        listeners()

        return view
    }

    private fun listeners() {
        controller.MyDrives("Bearer "+getStringVal(Constants.TOKEN))
        pd.show()
        pd.setContentView(R.layout.loading)
        future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
        future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
        past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
        past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));
        type = "future"

        future_bt.setOnClickListener {

            if (utility.isConnectingToInternet(context)) {
                future_bt.setBackground(getResources().getDrawable(R.drawable.leftfillblack));
                future_bt.setTextColor(getResources().getColor(R.color.colorWhite));
                past_bt.setTextColor(getResources().getColor(R.color.colorBlack));
                past_bt.setBackground(getResources().getDrawable(R.drawable.rightblackborder));
                type = "future"
                setData(futureRides,pastRides)

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
                type = "past"
                setData(futureRides,pastRides)
            } else {
                context?.registerReceiver(
                    broadcastReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    private fun findIds(view: View?) {

        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        future_bt = view?.findViewById(R.id.future_bt)!!
        past_bt = view?.findViewById(R.id.past_bt)
        drives_recyler = view?.findViewById(R.id.drives_recyler)
        utility = Utility()
        controller = Controller()
        controller.Controller(this,this)
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

    companion object {
        var deleterideidIf : DeleteRideID_IF?=null
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

    override fun onMyDrivesSuccess(success: Response<MyDrivesResponse>) {
        pd.dismiss()
        futureRides = ArrayList()
        pastRides = ArrayList()

        if (success.isSuccessful)
        {
            if (success.code()==200)
            {
                futureRides.addAll(success.body()?.futureRides!!)
                pastRides.addAll(success.body()?.completedRides!!)

                setData(futureRides,pastRides)

            } else {
                utility!!.relative_snackbar(
                    requireActivity().window.decorView,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }

    }

    private fun setData(
        futureRides: java.util.ArrayList<MyDrivesResponse.FutureRide>,
        pastRides: java.util.ArrayList<MyDrivesResponse.CompletedRide>
    ) {
        drives_recyler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        myDrivesAdapter = MyDrivesAdapter(context!!,type,futureRides,pastRides,
            getStringVal(Constants.NAME)!!
        )
        drives_recyler.adapter = myDrivesAdapter
    }

    override fun onDeleteRideSuccess(success: Response<DeleteRideResponse>) {
       if (success.isSuccessful)
       {
           if (success.body()?.deleted==true)
           {
               controller.MyDrives("Bearer "+getStringVal(Constants.TOKEN))
           } else {
               utility!!.relative_snackbar(
                   requireActivity().window.decorView,
                   success.message(),
                   getString(R.string.close_up)
               )
           }
       } else {
           utility!!.relative_snackbar(
               requireActivity().window.decorView,
               success.message(),
               getString(R.string.close_up)
           )
       }

    }


    override fun onError(error: String) {
        pd.dismiss()
        utility!!.relative_snackbar(
            requireActivity().window.decorView,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?) {
        pd.show()
        controller.DeleteRide("Bearer "+getStringVal(Constants.TOKEN), id.toString())
    }

}