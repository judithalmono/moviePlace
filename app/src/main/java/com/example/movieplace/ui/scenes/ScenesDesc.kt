package com.example.movieplace.ui.scenes

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.movieplace.Main
import com.example.movieplace.R
import com.example.movieplace.data.model.Scene
import com.example.movieplace.data.model.Vote
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import java.util.*
import com.example.movieplace.data.Result
import com.google.android.gms.location.LocationServices
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState


class ScenesDesc : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var scene: Scene
    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var scenesViewModel: SceneViewModel
    private lateinit var scrollView: ScrollView
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = true
    private val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenes_desc)

        val arguments = intent.extras
        scenesViewModel = ViewModelProvider(this)[SceneViewModel::class.java]
        scene = GsonBuilder().create().fromJson(arguments?.getString("scene").toString(), Scene::class.java)
        title = scene.Name
        scrollView = findViewById(R.id.scrollView)

        val imgScene = findViewById<ImageView>(R.id.imgScene)
        Glide.with(this).load(scene.img).centerCrop().into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
            ) {
                imgScene.setImageDrawable(resource)
            }
        })


        val rateBar = findViewById<RatingBar>(R.id.rateBar)
        rateBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, p1, _ ->
                Log.d("Rate", p1.toString())
                val vote = Vote(scene.id, p1)
                scenesViewModel.updateVote(vote).observe(
                    this, {
                        if (it is Result.Success) {
                            Toast.makeText(scrollView.context, "Update Vote", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

        val textLoc = findViewById<TextView>(R.id.textLocationDesc)
        textLoc.text = scene.nameLoc

        val textDesc = findViewById<TextView>(R.id.textDesc)
        textDesc.text = scene.Description


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapDesc) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        latitude = scene.location[0]
        longitude = scene.location[1]

        val place = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(place).title(scene.Name))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 16.0f))


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
