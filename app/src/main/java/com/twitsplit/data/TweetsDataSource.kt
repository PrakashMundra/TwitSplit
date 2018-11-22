package com.twitsplit.data

import android.arch.lifecycle.LiveData
import com.twitsplit.data.model.Tweet

interface TweetsDataSource {
    fun getTweetsList(): LiveData<List<Tweet>>

    fun saveTweet(tweet: Tweet)
}