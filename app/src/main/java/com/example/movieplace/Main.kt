package com.example.movieplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.movieplace.ui.login.Login

class Main : AppCompatActivity() {

//    private val CLIENT_ID = "0acce0e239094e678c135403cb8baaf7"
//    private val REDIRECT_URI = "com.example.movieplace://callback"
//    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_login)
        button.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
//            .setRedirectUri(REDIRECT_URI)
//            .showAuthView(true)
//            .build()
//        SpotifyAppRemote.connect(this, connectionParams,
//            object : Connector.ConnectionListener {
//                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
//                    mSpotifyAppRemote = spotifyAppRemote
//                    Log.d("MainActivity", "Connected! Yay!")
//
//                    // Now you can start interacting with App Remote
//                    connected()
//                }
//
//                override fun onFailure(throwable: Throwable) {
//                    Log.e("MyActivity", throwable.message, throwable)
//
//                    // Something went wrong when attempting to connect! Handle errors here
//                }
//            })
//    }
//
//    override fun onStop() {
//        super.onStop()
//        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
//    }
//
//    private fun connected() {
//        // Play a playlist
//        mSpotifyAppRemote!!.playerApi.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL")
//
//        // Subscribe to PlayerState
//        mSpotifyAppRemote!!.playerApi
//            .subscribeToPlayerState()
//            .setEventCallback { playerState: PlayerState ->
//                val track = playerState.track
//                if (track != null) {
//                    Log.d("MainActivity", track.name + " by " + track.artist.name)
//                }
//            }
//    }
}