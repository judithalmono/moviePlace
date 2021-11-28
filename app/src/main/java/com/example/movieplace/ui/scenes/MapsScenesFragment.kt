package com.example.movieplace.ui.scenes

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.*
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.movieplace.R
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.*
import kotlin.collections.ArrayList

class MapsScenesFragment : Fragment() {

    companion object {
        fun newInstance(id_movie: Int) =
            MapsScenesFragment().apply {
                arguments = bundleOf("movie_id" to id_movie)
            }
    }

    private lateinit var mMap: GoogleMap
    private lateinit var viewSeek: View
    private lateinit var seekBarDistance: SeekBar
    private lateinit var textViewDistance: TextView
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var locationManager : LocationManager? = null
    private lateinit var locationUser: Location
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var scenesViewModel: SceneViewModel
    private lateinit var scenesListAdapter: MyScenesRecyclesViewAdapter
    private var idmovie: Int = 0
    private var scenes: List<Scene> = ArrayList()
    private var scenesList: MutableList<Scene> = ArrayList()
    private var currentScenes: MutableList<Scene> = ArrayList()

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
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(locationUser.latitude, locationUser.longitude), 12.5f))
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
            // set marker in place
            val place = LatLng(latitude, longitude)
            mMap.addMarker(MarkerOptions().position(place).title(item.Name))

            mMap.setOnInfoWindowClickListener {
                // al clicar al título, se abre la pantalla con la info de la activity
                val intent = Intent(context, ScenesDesc::class.java)
                intent.putExtra("scene", GsonBuilder().create().toJson(item))
                context?.startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_scenes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = (childFragmentManager.findFragmentById(R.id.mapScenes) as SupportMapFragment?)!!

        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager?

        scenesViewModel = ViewModelProvider(this).get(SceneViewModel::class.java)
        scenesListAdapter = MyScenesRecyclesViewAdapter(context as ScenesActivity)

        viewSeek = view.findViewById(R.id.viewSeekBar)
        seekBarDistance = view.findViewById(R.id.seekBarDistance)
        textViewDistance = view.findViewById(R.id.textViewDistance)

        idmovie = (requireArguments().get("movie_id") as Int?)!!

        scenesViewModel.getScenesByID(idmovie).observe(
            viewLifecycleOwner,
            {
                if (it is Result.Success) {
                    scenes = it.data
                    scenesListAdapter.setData(scenes)
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
}