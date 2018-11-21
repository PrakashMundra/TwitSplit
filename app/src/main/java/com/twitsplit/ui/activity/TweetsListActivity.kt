package com.twitsplit.ui.activity

import android.content.Intent
import android.os.Bundle
import com.twitsplit.R
import com.twitsplit.ui.fragment.TweetsListFragment
import kotlinx.android.synthetic.main.activity_tweets_list.*

class TweetsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets_list)
        add_tweet.setOnClickListener {
            startActivity(Intent(this, AddTweetActivity::class.java))
        }
        if (savedInstanceState == null)
            addFragment(TweetsListFragment(), TweetsListFragment.TAG)
    }
}
