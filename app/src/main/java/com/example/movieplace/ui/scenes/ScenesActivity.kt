package com.example.movieplace.ui.scenes

import android.content.Intent
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.button_change) {
            if (selectedOption) {
                selectedOption = false
                item.icon = resources.getDrawable(R.drawable.listicon)
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MapsScenesFragment.newInstance())
//                    .commitNow()
                val intent = Intent(this, MapsScenesFragment::class.java)
                intent.putExtra("movie", GsonBuilder().create().toJson(movie))
                this?.startActivity(intent)
            } else {
                selectedOption = true
                item.icon = resources.getDrawable(R.drawable.locationicon)
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, ScenesList.newInstance(movie))
//                    .commitNow()

                val intent = Intent(this, ScenesList::class.java)
                intent.putExtra("movie", GsonBuilder().create().toJson(movie))
                this?.startActivity(intent)
            }
        }
        return true
    }
}