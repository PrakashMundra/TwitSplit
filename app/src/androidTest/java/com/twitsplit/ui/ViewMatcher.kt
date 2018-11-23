package com.twitsplit.ui

import android.support.test.espresso.core.internal.deps.guava.base.Preconditions
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.twitsplit.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class ViewMatcher {
    companion object {
        fun withItemText(itemText: String): Matcher<View> {
            Preconditions.checkArgument(!TextUtils.isEmpty(itemText), "ItemText cannot be null or empty")
            return object : TypeSafeMatcher<View>() {
                private var matched = false

                public override fun matchesSafely(item: View): Boolean {
                    val tweet = item.findViewById<TextView>(R.id.item_tweet)
                    if (tweet != null && tweet.text.contains(itemText) && !matched) {
                        this.matched = true
                        return true
                    }
                    return false
                }

                override fun describeTo(description: Description) {
                    description.appendText("RecyclerView with text $itemText")
                }
            }
        }
    }
}