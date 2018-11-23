package com.twitsplit.ui

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.twitsplit.R
import com.twitsplit.data.TweetsDataRepository
import com.twitsplit.data.local.AppDatabase
import com.twitsplit.data.local.TweetsLocalDataSource
import com.twitsplit.ui.activity.TweetsListActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TweetsListActivityTest {
    @Rule
    @JvmField
    val mTweetsListActivityRule = ActivityTestRule(TweetsListActivity::class.java)

    @Before
    fun setUp() {
        val appDatabase = AppDatabase.getInstance(InstrumentationRegistry.getTargetContext())!!
        TweetsDataRepository.getInstance(appDatabase.tweetsDao())!!.deleteAllTweets()
    }

    @After
    fun closeDB() {
        AppDatabase.clearInstance()
        TweetsLocalDataSource.clearInstance()
    }

    @Test
    fun clickAddTweetButton() {
        onView(withId(R.id.add_tweet)).perform(click())
        onView(withId(R.id.add_tweet)).check(matches(isDisplayed()))
    }

    @Test
    fun addNewTweet() {
        val tweetMessage = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        onView(withId(R.id.add_tweet)).perform(click())
        onView(withId(R.id.add_tweet)).perform(clearText(), typeText(tweetMessage), closeSoftKeyboard())
        onView(withId(R.id.save_tweet)).perform(click())
        onView(ViewMatcher.withItemText("do it myself")).check(matches(isDisplayed()))
    }
}