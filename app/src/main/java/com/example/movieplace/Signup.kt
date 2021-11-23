package com.example.movieplace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieplace.R

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}