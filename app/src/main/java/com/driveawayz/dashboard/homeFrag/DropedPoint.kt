package com.driveawayz.dashboard.homeFrag

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.R
import com.driveawayz.Utilities.GpsTracker
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.homeFrag.customPlacepicker.AutoCompleteAdapter

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
import java.io.IOException
import java.util.*

class DropedPoint : BaseFrag(),OnMapReadyCallback {

    private lateinit var setDestination_bt: Button
    lateinit var manager: FragmentManager
    var mapviewdrop : SupportMapFragment? = null
    private lateinit var setdestination_et : AutoCompleteTextView
    lateinit var mMap: GoogleMap
    private lateinit var gpsTracker: GpsTracker
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    var placesClient: PlacesClient? = null
    lateinit var adapter: AutoCompleteAdapter
    private lateinit var utility: Utility

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = inflater.inflate(R.layout.fragment_droped_point, container, false)
        autocompleteAddress()
        Places.initialize(context!!, getResources().getString(R.string.googleclientId));

        findIds(view)
        manager = getActivity()?.getSupportFragmentManager()!!

        mapviewdrop = manager.findFragmentById(R.id.mapviewdrop) as SupportMapFragment?
        gpsTracker = GpsTracker(context)
        mapviewdrop?.getMapAsync(this)


        if (gpsTracker.canGetLocation()) {
            lat = gpsTracker.latitude
            lng = gpsTracker.longitude

        } else {
            gpsTracker.showSettingsAlert()
        }

        listeners()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapviewdrop) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun listeners() {
        setDestination_bt.setOnClickListener {
            if (utility.isConnectingToInternet(context))
            {
                manager.beginTransaction().replace(R.id.nav_host_fragment,BookDriver()).addToBackStack(null).commit()
            } else {
                context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }

        }
    }

    private fun findIds(view: View?) {
        setDestination_bt = view?.findViewById(R.id.setDestination_bt)!!
        setdestination_et = view?.findViewById(R.id.setdestination_et)!!
        setdestination_et.threshold = 1
        setdestination_et.setOnItemClickListener(autocompleteClickListener)
        adapter = AutoCompleteAdapter(context,placesClient)
        setdestination_et.setAdapter(adapter)
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

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onStart() {
        super.onStart()
        mapviewdrop?.getMapAsync(this)
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
        AdapterView.OnItemClickListener { adapterView, view, i, l ->
            try {
                val item: AutocompletePrediction? = adapter.getItem(i) as AutocompletePrediction?
                var placeID: String? = null
                if (item != null) {
                    placeID = item.getPlaceId()
                }

                // To specify which data types to return, pass an array of Place.Fields in your FetchPlaceRequest
                // Use only those fields which are required.
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

                                val lat2: Double =
                                    java.lang.String.valueOf(addresses.get(0).latitude).toDouble()
                                val lng2: Double =
                                    java.lang.String.valueOf(addresses.get(0).longitude).toDouble()

                                lat = lat2
                                lng = lng2
                                Log.d("LATLONG", "" + lat2 + "   " + lng2)
                                val location = LatLng(lat, lng)
                                Log.d("LATLNG", "" + lat + "   " + lng)
                                setdestination_et.setText(addresses.get(0).getAddressLine(0).toString())

                                mMap.clear()

                                if (mapviewdrop != null) {
                                    mapviewdrop!!.onCreate(null);
                                    mapviewdrop!!.onResume();
                                    mapviewdrop!!.getMapAsync(this);
                                }

                                mMap.addMarker(
                                    MarkerOptions().position(location).title(
                                        addresses.get(0).getAddressLine(0) + "    " + addresses.get(
                                            0
                                        ).latitude + "   " + addresses.get(0).longitude
                                    )
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.loc1))
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
}