package com.virender.sampleapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RepoAdapter(val context: Context, val repoList:ArrayList<UserRepo>): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singlerowrecyclerview,parent,false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
       val data = repoList[position]
        holder.repoName.text = data.raponame
        holder.repoDes.text = data.repodesc
        holder.share.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                val url = data.repourl
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
        holder.CardView.setOnClickListener {
            val url = data.repourl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }
    }
    class RepoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val repoName = view.findViewById<TextView>(R.id.tvRepoName)
        val repoDes = view.findViewById<TextView>(R.id.tvRepoDes)
        val share = view.findViewById<ImageView>(R.id.ivShare)
        val CardView = view.findViewById<CardView>(R.id.cvcontainer)

    }


}