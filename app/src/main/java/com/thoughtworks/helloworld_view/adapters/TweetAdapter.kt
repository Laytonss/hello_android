package com.thoughtworks.helloworld_view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.viewModel.TweetData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TweetAdapter(private val tweetDataListFlow: Flow<List<TweetData>>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    private lateinit var tweetDataList: List<TweetData>

    init {
        MainScope().launch(Dispatchers.IO) {
            tweetDataListFlow.collectLatest {
                Log.d("room", "flow里的值为 ${it}")
                withContext(Dispatchers.Main) {
                    tweetDataList = it
                    notifyDataSetChanged()
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val imageView: ImageView
        val contentView: TextView

        init {
            imageView = view.findViewById(R.id.avatar_view_id)
            nameView = view.findViewById(R.id.name_view_id)
            contentView = view.findViewById(R.id.content_view_id)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.tweet_item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (::tweetDataList.isInitialized && position < tweetDataList.size) {
            viewHolder.imageView.load(R.drawable.avatar) {
                transformations(CircleCropTransformation())
            }
            viewHolder.nameView.text = tweetDataList[position].sender?.userName
            viewHolder.contentView.text = tweetDataList[position].tweet.content
        }
    }

    override fun getItemCount() = if (::tweetDataList.isInitialized) tweetDataList.size else 0
}