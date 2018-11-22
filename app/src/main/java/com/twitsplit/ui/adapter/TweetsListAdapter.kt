package com.twitsplit.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.twitsplit.R
import com.twitsplit.data.model.Tweet
import com.twitsplit.databinding.ListItemBinding

class TweetsListAdapter : RecyclerView.Adapter<TweetsListAdapter.ViewHolder>() {
    private var mTweetList: List<Tweet>? = null

    fun setTweets(tweets: List<Tweet>) {
        if (mTweetList == null) {
            mTweetList = tweets
            notifyItemRangeInserted(0, mTweetList?.size!!)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mTweetList?.size!!
                }

                override fun getNewListSize(): Int {
                    return tweets.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mTweetList?.get(oldItemPosition)!!.id == tweets[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newTweet = tweets[newItemPosition]
                    val oldTweet = mTweetList?.get(oldItemPosition)!!
                    return (newTweet.id == oldTweet.id && newTweet.tweet == oldTweet.tweet)
                }
            })
            mTweetList = tweets
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (mTweetList != null) mTweetList?.size!! else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tweet = mTweetList?.get(position)
    }

    class ViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}