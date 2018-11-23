package com.twitsplit.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.twitsplit.R
import com.twitsplit.ui.activity.AddTweetActivity
import com.twitsplit.ui.activity.TweetsListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddTweetActivityTest {
    @Rule
    @JvmField
    val mAddTweetActivityRule = ActivityTestRule(AddTweetActivity::class.java)

    @Test
    fun addNewTweet() {
        val tweetMessage = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        onView(withId(R.id.add_tweet)).perform(clearText(), typeText(tweetMessage), closeSoftKeyboard())
        onView(withId(R.id.save_tweet)).perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, TweetsListActivity::class.java)
        context.startActivity(intent)
        onView(ViewMatcher.withItemText("do it myself")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}