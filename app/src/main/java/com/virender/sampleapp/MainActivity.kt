package com.virender.sampleapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.virender.sampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(getString(R.string.sharedpreference), Context.MODE_PRIVATE)
        val isLogedIn = sharedPreferences.getBoolean("isLogedIn", false)
        if(isLogedIn){
            startActivity(Intent(this@MainActivity, LandingActivity::class.java))
        }

        binding.btncontinue.setOnClickListener {
            val username = binding.etusername.text
            sharedPreferences.edit()
                .putString("username",username.toString()).apply()
            sharedPreferences.edit().putBoolean("isLogedIn", true).apply()
            startActivity(Intent(this@MainActivity, LandingActivity::class.java))
            finishAffinity()
        }

    }
}