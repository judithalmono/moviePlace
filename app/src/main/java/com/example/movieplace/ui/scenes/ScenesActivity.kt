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
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState


class ScenesActivity: AppCompatActivity() {

    private lateinit var movie: Movie
    private var selectedOption: Boolean = true
    private val CLIENT_ID = "0acce0e239094e678c135403cb8baaf7"
    private val REDIRECT_URI = "http://nattech.fib.upc.edu:40401"
    var mSpotifyAppRemote: SpotifyAppRemote? = null

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

    override fun onStart() {
        Log.d("ENTRO", "AQUI")
        super.onStart()
        val connectionParams = com.spotify.android.appremote.api.ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("MainActivity", "Connected! Yay!")
                    connected()
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("MyActivity", throwable.message, throwable)

                    // Something went wrong when attempting to connect! Handle errors here
                }
            })
    }

    override fun onStop() {
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            mSpotifyAppRemote?.playerApi?.pause()
            finish()
            return true
        }
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

    private fun connected() {
        mSpotifyAppRemote!!.playerApi.play(movie.Album)

        // Subscribe to PlayerState
        mSpotifyAppRemote!!.playerApi
            .subscribeToPlayerState()
            .setEventCallback { playerState: PlayerState ->
                playerState.playbackOptions.repeatMode
                val track = playerState.track
                if (track != null) {
                    Log.d("Song:", track.name + " by " + track.artist.name)
                }
            }
    }
}