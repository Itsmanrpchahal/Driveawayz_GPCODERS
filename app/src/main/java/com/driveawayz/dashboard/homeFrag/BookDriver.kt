package com.driveawayz.dashboard.homeFrag

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSpinner
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.homeFrag.response.BookRide
import com.driveawayz.dashboard.homeFrag.response.MyVehicleRateResponse
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
import com.google.android.gms.maps.model.LatLng
import retrofit2.Response
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class BookDriver : BaseFrag(), Controller.MyVehiclesAPI, Controller.RateAPI,
    Controller.BookRideAPI {

    private lateinit var bookdriver_bt: Button
    private lateinit var popup: Dialog
    private lateinit var okpopup: Dialog
    private lateinit var cancel_bt: Button
    private lateinit var utility: Utility
    private lateinit var accept_bt: Button
    private lateinit var bookdoctv: TextView
    private lateinit var pick_date_et: EditText
    private lateinit var picktime_et: EditText
    private lateinit var hours_et: EditText
    private lateinit var numberofgueststv: EditText
    private lateinit var select_vehicle_spinner: AppCompatSpinner
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private lateinit var myVehicles: ArrayList<MyVehiclesResponse>
    private lateinit var myVehiclesName: ArrayList<String>
    var price: Int = 0
    val c = Calendar.getInstance()
    var vehicleID: Int = 0
    var total: Float = 0.0f
    var datetime: String = ""
    var lat : Double =0.0;
    var lng : Double=0.0
    var totalKM : Double = 0.0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View

        view = inflater.inflate(R.layout.fragment_book_driver, container, false)
        lat = arguments?.getDouble("lat")!!
        lng = arguments?.getDouble("lng")!!
        findIds(view)

        listeners()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun listeners() {
        bookdriver_bt.setOnClickListener {

            when {
                numberofgueststv.text.isEmpty() -> {
                    numberofgueststv.requestFocus()
                    numberofgueststv.setError("Enter number of guests")
                }

                hours_et.text.isEmpty() -> {
                    hours_et.requestFocus()
                    hours_et.setError("Enter number of hours")
                }

                pick_date_et.text.isEmpty() -> {
                    pick_date_et.requestFocus()
                    pick_date_et.setError("Enter pickup date")
                }

                picktime_et.text.isEmpty() -> {
                    picktime_et.requestFocus()
                    picktime_et.setError("Enter pickup time")
                }
                else -> {
                    if (utility.isConnectingToInternet(context)) {
                        Dialog()
                    } else {
                        context?.registerReceiver(
                            broadcastReceiver,
                            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                        )
                    }
                }
            }
        }

        var dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        }

        pick_date_et.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                context!!,
                R.style.DialogTheme,
                dateSetListener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()
        }


        picktime_et.setOnClickListener {
            if (pick_date_et.text.isEmpty()) {
                pick_date_et.requestFocus()
                utility!!.relative_snackbar(
                    requireActivity().window.decorView,
                    "Select date first",
                    getString(R.string.close_up)
                )
            } else {
                val cal = Calendar.getInstance()
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)

                        val sdf = SimpleDateFormat("dd/MM/yyyy")
                        val currentDate = sdf.format(Date())

                        val sdf1 = SimpleDateFormat("dd/MM/yyyy")
                        val selectedDate = sdf1.format(c.timeInMillis)

                        System.out.println(" C DATE is  " + currentDate + "  =>   " + selectedDate)
                        if (currentDate == selectedDate) {
                            if (c.timeInMillis <= cal.timeInMillis) {
                                Log.d("time", "" + c.timeInMillis + "  " + cal.timeInMillis)
                                picktime_et.setText(SimpleDateFormat("HH:mm:ss").format(cal.time))
                            } else {
                                Toast.makeText(context, "Invalid Time", Toast.LENGTH_SHORT).show()
                                picktime_et.setText("")
                            }
                        } else {
                            picktime_et.setText(SimpleDateFormat("HH:mm").format(cal.time))
                        }


                    }
                TimePickerDialog(
                    context,
                    R.style.DialogTheme,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val format1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val format2 = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf1 = SimpleDateFormat(format1, Locale.UK)
        val sdf2 = SimpleDateFormat(format2, Locale.UK)
        datetime = sdf2.format(c.time)
        Log.d("TIME", "" + sdf2.format(c.time))
        pick_date_et.setText(sdf.format(c.time))
        picktime_et.setText("")


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

    private fun Dialog() {
        popup = Dialog(context!!)
        val window: Window? = popup.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        popup.setContentView(R.layout.bookdriverdialog)
        popup.setCancelable(true)
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()

        var rate: Float = java.lang.Float.valueOf(price.toString())
        val hourr: Float = java.lang.Float.valueOf(hours_et.text.toString())
        total =
            rate * hourr

        accept_bt = popup.findViewById(R.id.accept_bt)
        cancel_bt = popup.findViewById(R.id.cancel_bt)
        bookdoctv = popup.findViewById(R.id.bookdoctv)

        bookdoctv.setText(
            "Thank you for driving with us, we " +
                    "apperciate your bussiness. The hourly\n" +
                    "rate for our driver at starting desti-\n" +
                    "nation is " + "$ " + price + " per hour. By Clicking\n" +
                    "accept you allow DriverVille to pre-\n" +
                    "authorize your credit card for $ " + total +
                    ". You will be only be charged at the\n" +
                    "closing of drive the actual time used."
        )
        cancel_bt.setOnClickListener(View.OnClickListener { popup.dismiss() })

        accept_bt.setOnClickListener(View.OnClickListener {
            if (utility.isConnectingToInternet(context)) {
                pd.show()
                pd.setContentView(R.layout.loading)
                controller.RideBook(
                    "Bearer " + getStringVal(Constants.TOKEN),
                    getStringVal(Constants.PICKUPADDRESS)!!,
                    getStringVal(Constants.DROPADDRESS)!!,
                    getStringVal(Constants.LAT_D)!! + "," + getStringVal(Constants.LNG_D)!!,
                    getStringVal(Constants.LAT)!! + "," + getStringVal(Constants.LNG)!!,
                    numberofgueststv.text.toString(),
                    hours_et.text.toString(),
                    datetime + " " +
                            picktime_et.text.toString(),
                    vehicleID,
                    total.toString(),
                    totalKM.toString()
                )

//                                controller.RideBook("Bearer "+getStringVal(Constants.TOKEN),
//                    "Ludhiana",
//                    "Dsasua",
//                    "0.0,0.0",
//                    "0.0,0.0",
//                    "2",
//                   "2",
//                    "2021-03-16 04:40",
//                  9,
//                    "100.0"
//                )
            }

        })
    }

    private fun dialogOK() {
        okpopup = Dialog(context!!)
        val window: Window = okpopup.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        okpopup.setContentView(R.layout.thankspopup)
        okpopup.setCancelable(true)
        okpopup.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val ok_bt: Button
        ok_bt = okpopup.findViewById(R.id.ok_bt)
        ok_bt.setOnClickListener { okpopup.dismiss() }
        okpopup.show()
    }

    private fun findIds(view: View) {
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        controller = Controller()
        controller.Controller(this, this, this)
        controller.MyVehicles("Bearer " + getStringVal(Constants.TOKEN))
        pd.show()
        pd.setContentView(R.layout.loading)
        bookdriver_bt = view.findViewById(R.id.bookdriver_bt)
        select_vehicle_spinner = view.findViewById(R.id.select_vehicle_spinner)
        pick_date_et = view.findViewById(R.id.pick_date_et)
        picktime_et = view.findViewById(R.id.picktime_et)
        numberofgueststv = view.findViewById(R.id.numberofgueststv)
        hours_et = view.findViewById(R.id.hours_et)

        if (!getStringVal(Constants.LNG).equals("0.0") && !getStringVal(Constants.LAT).equals("0.0") && !getStringVal(
                Constants.LNG_D
            ).equals("0.0") && !getStringVal(Constants.LAT_D).equals("0.0"))
        {

            var pickloc = LatLng(getStringVal(Constants.LAT)!!.toDouble(),getStringVal(Constants.LNG)!!.toDouble())
            var dropLoc = LatLng(lat,lng)
            totalKM = CalculationByDistance(pickloc,dropLoc)

        }
    }

    fun CalculationByDistance(StartP: LatLng, EndP: LatLng): Double {
        val Radius = 6371 // radius of earth in Km
        val lat1 = StartP.latitude
        val lat2 = EndP.latitude
        val lon1 = StartP.longitude
        val lon2 = EndP.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = Radius * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec: Int = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec: Int = Integer.valueOf(newFormat.format(meter))
        Log.i(
            "Radius Value", "" + valueResult + "   KM  " + kmInDec
                    + " Meter   " + meterInDec
        )
        return Radius * c
    }



    override fun onMyVehiclesSuccess(success: Response<List<MyVehiclesResponse>>) {
        pd.dismiss()
        myVehicles = ArrayList()
        if (success.isSuccessful) {
            for (i in success.body()?.indices!!) {
                myVehicles.addAll(listOf(success.body()!!.get(i)))
            }
            setMyVehicles(myVehicles)
        }
    }

    private fun setMyVehicles(myVehicles: ArrayList<MyVehiclesResponse>) {

        myVehiclesName = ArrayList()
        for (i in 0 until myVehicles.size) {
            myVehiclesName.add(myVehicles.get(i).getMake().toString())
            val adapter = ArrayAdapter(context!!, R.layout.spinnertv, myVehiclesName)
            select_vehicle_spinner.adapter = adapter
            select_vehicle_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        select_vehicle_spinner.selectedItem
                        if (position != 0) {

                        }
                        controller.Rate(
                            "Bearer " + getStringVal(Constants.TOKEN),
                            myVehicles.get(position).getId().toString()
                        )

                        vehicleID = myVehicles.get(position).getId()!!
                        pd.show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
        }

    }

    override fun onRateSuccess(success: Response<MyVehicleRateResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 200) {
                price = success.body()?.getRate()!!

            } else {
                utility!!.relative_snackbar(
                    requireActivity().window.decorView,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        }
    }

    override fun onBookRideSuccess(success: Response<BookRide>) {
        pd.dismiss()
        if (success.isSuccessful) {
            popup.dismiss()
            dialogOK()
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

}