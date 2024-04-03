package com.thoughtworks.helloworld_view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.room.entity.Tweet

class TweetAdapter(private val tweetList: List<Tweet>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val imageView: ImageView
        val contentView: TextView
        val bottomTextView: TextView

        init {
            imageView = view.findViewById(R.id.avatar_view_id)
            nameView = view.findViewById(R.id.name_view_id)
            contentView = view.findViewById(R.id.content_view_id)
            bottomTextView = view.findViewById(R.id.bottom_text_id)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.tweet_item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageView.load(R.drawable.avatar) {
            transformations(CircleCropTransformation())
        }
        viewHolder.nameView.text = tweetList[position].sender?.userName
        viewHolder.contentView.text = tweetList[position].content
        viewHolder.bottomTextView.visibility = if (position == tweetList.size - 1) {
            TextView.VISIBLE
        } else {
            TextView.INVISIBLE
        }
    }

    override fun getItemCount() = tweetList.size
}