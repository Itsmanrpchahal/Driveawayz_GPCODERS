package com.driveawayz.dashboard.homeFrag

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.GpsTracker
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.homeFrag.customPlacepicker.AutoCompleteAdapter
import com.driveawayz.dashboard.setiingFrag.adatper.MyAddress_Adapter
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse
import com.driveawayz.splashScreen.MeResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import retrofit2.Response
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class PickUpPoint : BaseFrag(), OnMapReadyCallback, Controller.MyAdderessAPI {

    lateinit var manager: FragmentManager
    lateinit var setPickUpBt: Button
    lateinit var pickupEt: AutoCompleteTextView
    var mapviewpickup: SupportMapFragment? = null
    public lateinit var mMap: GoogleMap
    private lateinit var gpsTracker: GpsTracker
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    var placesClient: PlacesClient? = null
    lateinit var adapter: AutoCompleteAdapter
    private lateinit var controller: Controller
    private lateinit var pd: ProgressDialog
    private lateinit var select_address_spinner: AppCompatSpinner
    private lateinit var utility: Utility
    private lateinit var myAddresses: ArrayList<MyAddessesResponse>
    private lateinit var addressesList: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapviewpickup) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_pick_up, container, false)
        autocompleteAddress()
        Places.initialize(context!!, getResources().getString(R.string.googleclientId));
        findIds(view, savedInstanceState)
        controller = Controller()
        controller.Controller(this)
        controller.MyAddresss("Bearer " + getStringVal(Constants.TOKEN))
        pd.show()
        pd.setContentView(R.layout.loading)
        manager = getActivity()?.getSupportFragmentManager()!!

        mapviewpickup = manager.findFragmentById(R.id.mapviewpickup) as SupportMapFragment?
        gpsTracker = GpsTracker(context)
        mapviewpickup?.getMapAsync(this)


        if (gpsTracker.canGetLocation()) {
            lat = gpsTracker.latitude
            lng = gpsTracker.longitude
            val geocoder: Geocoder
            geocoder = Geocoder(context, Locale.getDefault())
            val address: List<Address>
            address = geocoder.getFromLocation(lat, lng, 1)

            //pickupEt.setText(address.get(0).getAddressLine(0))
        } else {
            gpsTracker.showSettingsAlert()
        }

        listeners()

        return view
    }

    private fun listeners() {
        setPickUpBt.setOnClickListener {


            if (utility.isConnectingToInternet(context)) {
                if (pickupEt.text.toString().equals("")) {
                    pickupEt.requestFocus()
                    pickupEt.setError("Enter Pickup address")
                } else {
                    setStringVal(Constants.LAT, lat.toString())
                    setStringVal(Constants.LNG, lng.toString())
                    setStringVal(Constants.PICKUPADDRESS, pickupEt.text.toString())

                    manager.beginTransaction().replace(
                        R.id.nav_host_fragment,
                        DropedPoint()
                    ).addToBackStack(null).commit()
                }

            } else {
                context?.registerReceiver(
                    broadcastReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }

        }
    }

    private fun findIds(view: View?, savedInstanceState: Bundle?) {
        setPickUpBt = view!!.findViewById(R.id.setPickUp_bt)
        pickupEt = view!!.findViewById(R.id.pickup_et)
        select_address_spinner = view.findViewById(R.id.select_address_spinner)
        pickupEt.threshold = 1
        pickupEt.setOnItemClickListener(autocompleteClickListener)
        adapter = AutoCompleteAdapter(context, placesClient)
        pickupEt.setAdapter(adapter)
        utility = Utility()
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
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

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onStart() {
        super.onStart()
        mapviewpickup?.getMapAsync(this)
        context?.registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val location = LatLng(lat, lat)

        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
        mMap.uiSettings?.isMyLocationButtonEnabled = true
        mMap.uiSettings?.isTiltGesturesEnabled = true
        mMap.uiSettings?.isCompassEnabled = true
        mMap.uiSettings?.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings?.isScrollGesturesEnabled = false

        val cameraPosition =
            CameraPosition.builder().target(LatLng(lat, lng)).zoom(16f).bearing(10f)
                .tilt(30f).build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun autocompleteAddress() {
        val apiKey = getString(R.string.googleclientId)
        if (apiKey.isEmpty()) {
            return
        }

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(context!!, apiKey)
        }
        placesClient = Places.createClient(context!!)
    }

    private val autocompleteClickListener =
        OnItemClickListener { adapterView, view, i, l ->
            try {
                val item: AutocompletePrediction? = adapter.getItem(i) as AutocompletePrediction?
                var placeID: String? = null
                if (item != null) {
                    placeID = item.getPlaceId()
                }

                //                To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
                //                Use only those fields which are required.
                val placeFields: List<Place.Field> = Arrays.asList(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.ADDRESS,
                    Place.Field.LAT_LNG
                )
                var request: FetchPlaceRequest? = null
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields)
                        .build()
                }
                if (request != null) {
                    placesClient?.fetchPlace(request)
                        ?.addOnSuccessListener(OnSuccessListener<FetchPlaceResponse> { task ->
                            val loc = task.place.toString()
                            val geocoder = Geocoder(context)
                            try {
                                val addresses: List<Address> = geocoder.getFromLocationName(loc, 15)

                                // val latLngs: MutableList<LatLng> = ArrayList(addresses.size)
                                // Log.d("latLngs", latLngs.toString())
                                9
                                val lat2: Double =
                                    java.lang.String.valueOf(addresses.get(0).latitude).toDouble()
                                val lng2: Double =
                                    java.lang.String.valueOf(addresses.get(0).longitude).toDouble()

                                lat = lat2
                                lng = lng2
                                Log.d("LATLONG", "" + lat2 + "   " + lng2)
                                val location = LatLng(lat, lng)
                                Log.d("LATLNG", "" + lat + "   " + lng)
                                pickupEt.setText(addresses.get(0).getAddressLine(0).toString())
                                pickupEt.setSelection(pickupEt.text.length)
                                mMap.clear()

                                if (mapviewpickup != null) {
                                    mapviewpickup!!.onCreate(null);
                                    mapviewpickup!!.onResume();
                                    mapviewpickup!!.getMapAsync(this);
                                }

                                mMap.addMarker(
                                    MarkerOptions().position(location).title(
                                        addresses.get(0).getAddressLine(0) + "    " + addresses.get(
                                            0
                                        ).latitude + "   " + addresses.get(0).longitude
                                    )
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                                )
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))

                                hideKeyboard()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        })?.addOnFailureListener(OnFailureListener { e ->
                            e.printStackTrace()
                        })
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun hideKeyboard() {
        try {
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onMyAddressSuccess(success: Response<List<MyAddessesResponse>>) {
        pd.dismiss()
        if (success.isSuccessful) {
            myAddresses = ArrayList()
            for (i in success.body()?.indices!!) {
                myAddresses.addAll(listOf(success.body()!!.get(i)))
            }

            addressesList = ArrayList()
            addressesList.add("Select address")
            for (i in 0 until myAddresses.size) {
                addressesList.add(myAddresses.get(i).address.toString())
                val adapter = ArrayAdapter(context!!, R.layout.spinnertv, addressesList)
                select_address_spinner.adapter = adapter
                select_address_spinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            select_address_spinner.selectedItem
                            if (position != 0) {

                                setStringVal(Constants.LAT, "0.0")
                                setStringVal(Constants.LNG, "0.0")
                                setStringVal(Constants.PICKUPADDRESS, myAddresses.get(position-1).address+" ,"+myAddresses.get(position-1).street)
                            }


                            //pd.show()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                    }
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
}