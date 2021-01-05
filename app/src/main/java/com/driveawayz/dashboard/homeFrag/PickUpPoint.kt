package com.driveawayz.dashboard.homeFrag

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.driveawayz.R
import com.driveawayz.Utilities.GpsTracker
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
import kotlin.collections.ArrayList


class PickUpPoint : Fragment(), OnMapReadyCallback {

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
        manager = getActivity()?.getSupportFragmentManager()!!

        mapviewpickup = manager.findFragmentById(R.id.mapviewpickup) as SupportMapFragment?
        gpsTracker = GpsTracker(context)
        mapviewpickup?.getMapAsync(this)


        if (gpsTracker.canGetLocation()) {
            lat = gpsTracker.latitude
            lng = gpsTracker.longitude
            pickupEt.setText(gpsTracker.latitude.toString() + " " + gpsTracker.longitude)

        } else {
            gpsTracker.showSettingsAlert()
        }

        listeners()

        return view
    }

    private fun listeners() {
        setPickUpBt.setOnClickListener {
            manager.beginTransaction().replace(
                R.id.nav_host_fragment,
                DropPoint()
            ).addToBackStack(null).commit()
        }
    }

    private fun findIds(view: View?, savedInstanceState: Bundle?) {
        setPickUpBt = view!!.findViewById(R.id.setPickUp_bt)
        pickupEt = view!!.findViewById(R.id.pickup_et)
        pickupEt.threshold = 1
        pickupEt.setOnItemClickListener(autocompleteClickListener)
        adapter = AutoCompleteAdapter(context,placesClient)
        pickupEt.setAdapter(adapter)
    }

    override fun onStart() {
        super.onStart()
        mapviewpickup?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val location = LatLng(lat, lat)
        mMap.addMarker(
            MarkerOptions().position(location).title("test")
                .icon(BitmapDescriptorFactory.defaultMarker())
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
        mMap.uiSettings?.isMyLocationButtonEnabled = true
        mMap.uiSettings?.isCompassEnabled = true
        mMap.uiSettings?.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

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
                                val latLngs: MutableList<LatLng> = ArrayList(addresses.size)
                                Log.d("latLngs", latLngs.toString())
                                for (a in addresses) {
                                    if (a.hasLatitude() && a.hasLongitude()) {
                                        latLngs.add(LatLng(a.getLatitude(), a.getLongitude()))
                                        val lat1: Double = java.lang.String.valueOf(a.getLatitude()).toDouble()
                                        val lng1: Double =
                                            java.lang.String.valueOf(a.getLongitude()).toDouble()
                                        lat = lat1
                                        lng = lng1

                                        //                                        getCityName(lat1, lng1);
                                        // location(lat1, lng1)
                                    }
                                }
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
}