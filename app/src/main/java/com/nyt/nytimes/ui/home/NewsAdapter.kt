package com.nyt.nytimes.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyt.nytimes.R
import com.nyt.nytimes.data.model.ResultsItem
import com.nyt.nytimes.ui.main.MainActivity
import com.squareup.picasso.Picasso


class NewsAdapter(
    private val context: Context?
) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var news: MutableList<ResultsItem?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = news[position]
        holder.bind(context, item)
        holder.itemView.setOnClickListener {
            item?.let { (context as MainActivity).newsDetailedPage(item) }
        }
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeading = view.findViewById<TextView>(R.id.tv_heading)
        private val tvContent = view.findViewById<TextView>(R.id.tv_content)
        private val ivAvatar = view.findViewById<ImageView>(R.id.iv_news_avatar)
        private val tvDate = view.findViewById<TextView>(R.id.tv_date)
        fun bind(context: Context?, content: ResultsItem?) {
            tvHeading.text = content?.title
            tvContent.text = content?.byline
            tvDate.text = content?.publishedDate
            context?.let {
                var url = ""
                if (content?.media?.isNotEmpty()!!) {
                    content.media[0]?.mediaMetadata?.forEach { item ->
                        if (item?.format == "Standard Thumbnail")
                            url = item.url ?: ""
                    }
                }
               try {
                   Picasso.with(context)
                       .load(url)
                       .placeholder(R.drawable.placeholder)
                       .error(R.drawable.placeholder)
                       .into(ivAvatar)
               }catch (e:Exception){
                   Picasso.with(context)
                       .load(R.drawable.placeholder)
                       .into(ivAvatar)
               }

            }
        }
    }

    fun updateList(list: List<ResultsItem?>) {
        news.clear()
        news.addAll(list)
    }

}