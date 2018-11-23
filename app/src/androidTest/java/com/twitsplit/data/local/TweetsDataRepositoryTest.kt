package com.twitsplit.data.local

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.twitsplit.data.LiveDataTestUtil
import com.twitsplit.data.model.Tweet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TweetsDataRepositoryTest {
    private lateinit var mAppDatabase: AppDatabase
    private lateinit var mTweetsLocalDataSource: TweetsLocalDataSource

    @Before
    fun setUp() {
        mAppDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).build()
        val tweetsDao = mAppDatabase.tweetsDao()
        TweetsLocalDataSource.clearInstance()
        mTweetsLocalDataSource = TweetsLocalDataSource.getInstance(tweetsDao)!!
    }

    @After
    fun closeDB() {
        mAppDatabase.close()
        AppDatabase.clearInstance()
        TweetsLocalDataSource.clearInstance()
    }

    @Test
    fun preConditions() {
        assertNotNull(mAppDatabase)
        assertNotNull(mTweetsLocalDataSource)
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertTweet() {
        val id = 1
        val tweetMessage = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val newTweet = Tweet(1, tweetMessage)
        mTweetsLocalDataSource.saveTweet(newTweet)
        val observer = mTweetsLocalDataSource.getTweet(id)
        val tweet = LiveDataTestUtil.getValue(observer)
        assertThat(tweet, notNullValue())
        assertThat(tweet.id, `is`<Int>(id))
        assertThat(tweet.tweet, `is`<String>(tweetMessage))
    }
}