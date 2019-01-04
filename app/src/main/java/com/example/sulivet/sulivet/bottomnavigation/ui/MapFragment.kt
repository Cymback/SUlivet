package com.example.sulivet.sulivet.bottomnavigation.ui


import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sulivet.sulivet.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false

    private var mMapView: MapView? = null
    private var googleMap: GoogleMap? = null


    companion object {
        val TAG: String = MapFragment::class.java.simpleName
        fun newInstance() = MapFragment()

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_map)
        val view = inflater.inflate(R.layout.fragment_glg_map_layout, container, false)
        mMapView = view.findViewById(R.id.mapView)


//        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
//        ActivityCompat.requestPermissions(activity!!, permissions, 0)


        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume()
        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView!!.getMapAsync { mMap ->
            googleMap = mMap
            googleMap!!.isMyLocationEnabled = true

            //To add marker
            val sydney = LatLng(-34.0, 151.0)
            googleMap!!.addMarker(MarkerOptions().position(sydney).title("Title").snippet("Marker Description"))
            // For zooming functionality
            val cameraPosition = CameraPosition.Builder().target(sydney).zoom(12f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
        return view
    }


    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    override fun onMapReady(p0: GoogleMap?) {
        setUpMap()


    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(context!!,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

        }

    }
}
