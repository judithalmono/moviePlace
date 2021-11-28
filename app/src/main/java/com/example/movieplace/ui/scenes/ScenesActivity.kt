package com.example.movieplace.ui.scenes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.google.gson.GsonBuilder
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat


class ScenesActivity: AppCompatActivity() {

    private lateinit var movie: Movie
    private var selectedOption: Boolean = true

    /**
     * This is executed when the scenes is launched for the first time or created again.
     * @param savedInstanceState is the instance of the saved State of the scene
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenes)


        val root = findViewById<View>(R.id.activity_listscenes)

        // recibir nombre de la escena seleccionada
        val arguments = intent.extras
        movie = GsonBuilder().create().fromJson(arguments?.getString("movie").toString(), Movie::class.java)
        title = movie.original_title
        Glide.with(this).load(movie.img).centerCrop().into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
            ) {
                root.background = resource
            }
        })

        // poner botón hacia atrás
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ScenesList.newInstance(movie.id))
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.button_change) {
            if (selectedOption) { // Map scenes
                selectedOption = false
                item.icon = ContextCompat.getDrawable(this, R.drawable.listicon)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MapsScenesFragment.newInstance(movie.id))
                    .commitNow()
            } else { //List scenes
                selectedOption = true
                item.icon = ContextCompat.getDrawable(this, R.drawable.locationicon)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ScenesList.newInstance(movie.id))
                    .commitNow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}