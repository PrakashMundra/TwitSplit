package com.twitsplit.data.local

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.twitsplit.data.LiveDataTestUtil
import com.twitsplit.data.model.Tweet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TweetsDaoTest {
    private lateinit var mAppDatabase: AppDatabase

    @Before
    fun initDB() {
        mAppDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
    }

    @After
    fun closeDB() {
        mAppDatabase.close()
        AppDatabase.clearInstance()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertTweet() {
        val id = 1
        val tweetMessage = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val newTweet = Tweet(1, tweetMessage)
        mAppDatabase.tweetsDao().insertTweet(newTweet)
        val tweetObserver = mAppDatabase.tweetsDao().getTweetById(1)
        val tweet = LiveDataTestUtil.getValue(tweetObserver)
        assertThat(tweet, notNullValue())
        assertThat(tweet.id, `is`<Int>(id))
        assertThat(tweet.tweet, `is`<String>(tweetMessage))
    }
}