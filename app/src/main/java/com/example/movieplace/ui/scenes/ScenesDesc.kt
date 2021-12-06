package com.example.movieplace.ui.scenes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.movieplace.R
import com.example.movieplace.data.model.Scene
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import java.util.*


class ScenesDesc : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var scene: Scene
    private lateinit var mMap: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenes_desc)

        val arguments = intent.extras
        scene = GsonBuilder().create().fromJson(arguments?.getString("scene").toString(), Scene::class.java)
        title = scene.Name

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
            RatingBar.OnRatingBarChangeListener { _, p1, _ -> Log.d("Rate", p1.toString()) }

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
