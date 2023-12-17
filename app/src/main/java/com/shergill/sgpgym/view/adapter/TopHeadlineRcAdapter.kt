package com.shergill.sgpgym.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.shergill.sgpgym.R
import com.shergill.sgpgym.model.Article
import com.shergill.sgpgym.model.ArticleX
import com.shergill.sgpgym.model.EveryThingResponce
import com.shergill.sgpgym.model.TopHeadlinesResponce

class TopHeadlineRcAdapter(var article:List<ArticleX>,var onclick:OnTopHeadlineClick):RecyclerView.Adapter<TopHeadlineRcAdapter.InnerClass>() {


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
                onclick.openUrl(currentPosition.url)
            }
        }
    }

    class InnerClass(view: View) :RecyclerView.ViewHolder(view){

        var ns = view.findViewById<TextView>(R.id.newtv)
        var profileImg = view.findViewById<ImageView>(R.id.profileNews)
        var date = view.findViewById<Chip>(R.id.date)

        fun bind(article: ArticleX){
            ns.text = article.title
//            profileImg.setImageURI(Uri.parse())
            Glide.with(this.profileImg.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.profile)
                .into(profileImg)
            if (article.author.isNullOrEmpty()||article.author==""){
//                date.visibility = View.GONE
                date.text = "Satwinder"
            }else{
                date.text = article.author.trim()
            }
        }
    }
    interface OnTopHeadlineClick{
        fun openUrl(url:String)
    }
}