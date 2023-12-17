package com.shergill.sgpgym.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.shergill.sgpgym.R
import com.shergill.sgpgym.model.Article
import com.shergill.sgpgym.model.EveryThingResponce

class NewRcAdapter(var article:List<Article>,var onTopNewsClick: OnTopNewsClick):RecyclerView.Adapter<NewRcAdapter.InnerClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerClass {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.news_rc_item,parent,false)
        return InnerClass(view)
    }

    override fun getItemCount(): Int {
        return article.size
    }

    override fun onBindViewHolder(holder: InnerClass, position: Int) {
        var currentPosition = article.get(position)


        holder.apply {
            bind(currentPosition)
            holder.itemView.setOnClickListener {
                onTopNewsClick.openUrl(currentPosition.url)
            }
        }
    }

    class InnerClass(view: View) :RecyclerView.ViewHolder(view){

        var ns = view.findViewById<TextView>(R.id.newtv)
        var profileImg = view.findViewById<ImageView>(R.id.profileNews)
        var date = view.findViewById<Chip>(R.id.date)

        fun bind(article: Article){
            ns.text = article.title
//            profileImg.setImageURI(article.urlToImage.toUri())
            Glide.with(this.profileImg.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.profile)
                .into(profileImg)
            if (article.author.isNullOrEmpty()||article.author==""){
//                date.visibility = View.GONE
                date.text = "Shergill Productions"
            }else{
                date.text = article.author.trim()
            }

        }
    }

    interface OnTopNewsClick{
        fun openUrl(url:String)
    }
}