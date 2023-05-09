package com.virender.sampleapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.virender.sampleapp.databinding.ActivityLandingBinding
import org.json.JSONArray
import org.json.JSONException

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding
    lateinit var sharedPreferences:SharedPreferences
    lateinit var username:String
    lateinit var recyclerAdapter: RepoAdapter
    lateinit var LayoutManager: RecyclerView.LayoutManager


    var repoInfoList = arrayListOf<UserRepo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progreslayout.visibility = View.VISIBLE
        sharedPreferences = getSharedPreferences(getString(R.string.sharedpreference), Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username","user").toString()
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "User's Repositories"
        binding.fbadd.setOnClickListener {
            startActivity(Intent(this@LandingActivity, Addrepo::class.java))

        }
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/users/${username.toString()}/repos"

        if(ConnectionManager().checkConnection(this)){
           val jsonArrayRequest = object : JsonArrayRequest(Method.GET,url,null,Response.Listener {
               binding.progreslayout.visibility = View.GONE

               try {
                   for (i in 0 until it.length()){
                       val data = it.getJSONObject(i)
                       val userrepo = UserRepo(data.getString("name"),
                           data.getString("description"),
                           data.getString("html_url"))
                       repoInfoList.add(userrepo)

                   }
                   LayoutManager = LinearLayoutManager(this)
                   recyclerAdapter = RepoAdapter(this@LandingActivity, repoInfoList)
                   binding.rvrepo.adapter = recyclerAdapter
                   binding.rvrepo.layoutManager = LayoutManager
               }catch (e: Error){
                   Toast.makeText(this, "Some unexpected error occured !!!", Toast.LENGTH_SHORT).show()
               }

           },Response.ErrorListener {
               Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()

           }){
               override fun getHeaders(): MutableMap<String, String> {
                   return super.getHeaders()
               }
           }
            queue.add(jsonArrayRequest)
        }

    }

    override fun onBackPressed() {
        finishAffinity()
    }


}