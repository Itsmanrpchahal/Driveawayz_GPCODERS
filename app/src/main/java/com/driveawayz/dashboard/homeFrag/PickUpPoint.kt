package com.driveawayz.dashboard.homeFrag

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.driveawayz.R
import com.driveawayz.Utilities.GpsTracker
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import java.util.*


class PickUpPoint : Fragment(), OnMapReadyCallback {

    lateinit var manager: FragmentManager
    lateinit var setPickUpBt: Button
    lateinit var pickupEt: EditText
    var mapviewpickup: SupportMapFragment? = null
    public lateinit var mMap: GoogleMap
    private lateinit var gpsTracker: GpsTracker
    private var lat: Double = 0.0
    private var lng: Double = 0.0

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
    }

    override fun onStart() {
        super.onStart()
        mapviewpickup?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val location = LatLng(lat, lat)
        mMap.addMarker(
            MarkerOptions().position(location).title("test").icon(BitmapDescriptorFactory.defaultMarker())
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
        mMap.uiSettings?.isMyLocationButtonEnabled = true
        mMap.uiSettings?.isCompassEnabled = true
        mMap.uiSettings?.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        val cameraPosition =
            CameraPosition.builder().target(LatLng(lat, lng)).zoom(12f).bearing(0f)
                .tilt(30f).build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


    }
}