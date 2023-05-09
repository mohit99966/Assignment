package com.virender.sampleapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.virender.sampleapp.databinding.ActivityAddrepoBinding


class Addrepo : AppCompatActivity() {
    lateinit var binding: ActivityAddrepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddrepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.btnAddRepo.setOnClickListener {
            val owner = binding.etvOwner.text
            val reponame = binding.etvRepoName.text
            if (owner.toString() != "" && reponame.toString() != ""){
                Toast.makeText(this, "Required Token", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                // todo: goto back activity from here
                val intent = Intent(this@Addrepo, LandingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}