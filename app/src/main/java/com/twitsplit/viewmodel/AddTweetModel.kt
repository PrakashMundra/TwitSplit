package com.twitsplit.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import android.widget.Toast
import com.twitsplit.App
import com.twitsplit.R
import com.twitsplit.data.TweetsDataRepository
import com.twitsplit.data.model.Tweet

class AddTweetModel(application: Application, private val repository: TweetsDataRepository) : AndroidViewModel(application) {
    val tweet = ObservableField<String>()

    fun saveTweet(): Boolean {
        val tweet = tweet.get()
        return if (tweet != null && tweet.trim().isNotEmpty()) {
            val splits = tweet.split(" ")
            if (tweet.length < 50 || (tweet.length > 50 && splits.size > 1)) {
                val newTweet = Tweet(tweet = tweet)
                repository.saveTweet(newTweet)
                true
            } else {
                Toast.makeText(getApplication<Application>().baseContext,
                        R.string.space_tweet_message, Toast.LENGTH_SHORT).show()
                false
            }
        } else {
            Toast.makeText(getApplication<Application>().baseContext,
                    R.string.empty_tweet_message, Toast.LENGTH_SHORT).show()
            false
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return AddTweetModel(application, (application as App).getDataRepository()) as T
        }
    }
}