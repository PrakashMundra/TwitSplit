package com.twitsplit.data.local

import android.arch.lifecycle.LiveData
import android.support.annotation.VisibleForTesting
import com.twitsplit.data.TweetsDataSource
import com.twitsplit.data.model.Tweet
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TweetsLocalDataSource(val executor: Executor, private val tweetsDao: TweetsDao) : TweetsDataSource {
    companion object {
        private var INSTANCE: TweetsLocalDataSource? = null

        fun getInstance(tweetsDao: TweetsDao): TweetsLocalDataSource? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = TweetsLocalDataSource(Executors.newSingleThreadExecutor(), tweetsDao)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun getTweetsList(): LiveData<List<Tweet>> {
        return tweetsDao.getTweetsList()
    }

    override fun saveTweet(tweet: Tweet) {
        executor.execute { tweetsDao.insertTweet(tweet) }
    }
}