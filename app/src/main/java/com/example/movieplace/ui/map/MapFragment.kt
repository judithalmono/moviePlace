package com.example.movieplace.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieplace.CustomInfoWindowForGoogleMap
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene
import com.example.movieplace.databinding.FragmentMapBinding
import com.example.movieplace.ui.scenes.MyScenesRecyclesViewAdapter
import com.example.movieplace.ui.scenes.SceneViewModel
import com.example.movieplace.ui.scenes.ScenesActivity
import com.example.movieplace.ui.scenes.ScenesDesc
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MapFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel
//    private lateinit var mapScenesAdapter: MyMapRecyclesViewAdapter
    private var _binding: FragmentMapBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var locationManager : LocationManager? = null
    private lateinit var locationUser: Location
    private lateinit var mapFragment: SupportMapFragment
    private var scenes: List<Scene> = ArrayList()
    private var scenesList: MutableList<Scene> = ArrayList()
    private var currentScenes: MutableList<Scene> = ArrayList()
    private lateinit var marker: Marker

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        val barcelona = LatLng(41.3879, 2.16992)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 12.5f))

        Dexter.withContext(context)
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(object: MultiplePermissionsListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(response: MultiplePermissionsReport?) {
                    if (response!!.areAllPermissionsGranted()) {
                        mMap.isMyLocationEnabled = true;
                        mMap.uiSettings.isMyLocationButtonEnabled = true;
                        val locationListener = object: LocationListener{
                            override fun onLocationChanged(location: Location) {
                                locationUser = location
                            }
                            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                            }

                            override fun onProviderEnabled(provider: String) {
                            }

                            override fun onProviderDisabled(provider: String) {
                            }

                        }
                        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            }).check()

        for (item in currentScenes) {

            // transform address to coordinates
//            val geocoder = Geocoder(context, Locale.getDefault())
            latitude = item.location[0]
            longitude = item.location[1]
//            val addresses: List<Address> =
//                geocoder.getFromLocationName(item.nomCarrer + " " + item.numCarrer, 1)
//            if (addresses.isNotEmpty()) {
//                latitude = addresses[0].latitude
//                longitude = addresses[0].longitude
//            }

            val place = LatLng(latitude, longitude)

            marker = mMap.addMarker(
                MarkerOptions()
                .position(place)
                .title(item.Name)
                .snippet(item.Description))!!

            marker.tag = item

            mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(requireContext()))

            mMap.setOnInfoWindowClickListener {

                // al clicar al título, se abre la pantalla con la info de la activity
                val intent = Intent(context, ScenesDesc::class.java)
                intent.putExtra("scene", GsonBuilder().create().toJson(it.tag))
                context?.startActivity(intent)
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel =
            ViewModelProvider(this)[MapViewModel::class.java]

        _binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!

        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]
//        mapScenesAdapter = MyScenesRecyclesViewAdapter(context)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        mapFragment.getMapAsync(callback)

        mapViewModel.getAllScenes().observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    scenes = it.data
                    for (item in scenes) {
                        scenesList.add(item)
                    }
//                    progressBar.visibility = View.GONE
                }
                currentScenes = scenesList
                mapFragment.getMapAsync(callback)
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}