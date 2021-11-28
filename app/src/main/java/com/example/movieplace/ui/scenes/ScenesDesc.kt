package com.example.movieplace.ui.scenes

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.Scene
import com.google.gson.GsonBuilder




class ScenesDesc : AppCompatActivity() {

    private lateinit var scene: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenes_desc)

        val arguments = intent.extras
        scene = GsonBuilder().create().fromJson(arguments?.getString("scene").toString(), Scene::class.java)
        title = scene.Name

        val imgScene = findViewById<ImageView>(R.id.imgScene)
        imgScene.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background15))

        val rateBar = findViewById<RatingBar>(R.id.rateBar)
        rateBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, p1, _ -> Log.d("Rate", p1.toString()) }

//        val textLoc = findViewById<TextView>(R.id.textLocationDesc)
//        textLoc.text = scene.location

//        val textDesc = findViewById<TextView>(R.id.textDesc)
//        textDesc.text = scene.scene_dsc

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}