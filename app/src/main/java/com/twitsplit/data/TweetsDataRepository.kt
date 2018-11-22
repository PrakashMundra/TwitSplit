package com.twitsplit.data

import android.arch.lifecycle.LiveData
import android.support.annotation.VisibleForTesting
import com.twitsplit.data.local.AppDatabase
import com.twitsplit.data.local.TweetsDao
import com.twitsplit.data.local.TweetsLocalDataSource
import com.twitsplit.data.model.Tweet

class TweetsDataRepository(private val tweetsLocalDataSource: TweetsLocalDataSource) : TweetsDataSource {
    companion object {
        private var INSTANCE: TweetsDataRepository? = null

        fun getInstance(tweetsDao: TweetsDao): TweetsDataRepository? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = TweetsDataRepository(TweetsLocalDataSource.getInstance(tweetsDao)!!)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            TweetsLocalDataSource.clearInstance()
            INSTANCE = null
        }
    }

    override fun getTweetsList(): LiveData<List<Tweet>> {
        return tweetsLocalDataSource.getTweetsList();
    }

    override fun saveTweet(tweet: Tweet) {
        return tweetsLocalDataSource.saveTweet(tweet)
    }

    override fun getTweet(tweetId: Int): LiveData<Tweet> {
        return tweetsLocalDataSource.getTweet(tweetId)
    }

    override fun deleteAllTweets() {
        return tweetsLocalDataSource.deleteAllTweets()
    }
}