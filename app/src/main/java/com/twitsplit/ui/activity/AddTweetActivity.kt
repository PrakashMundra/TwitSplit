package com.twitsplit.ui.activity

import android.os.Bundle
import com.twitsplit.R
import com.twitsplit.ui.fragment.AddTweetFragment
import kotlinx.android.synthetic.main.activity_add_tweet.*

class AddTweetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tweet)
        save_tweet.setOnClickListener {}
        if (savedInstanceState == null)
            addFragment(AddTweetFragment(), AddTweetFragment.TAG)
        setUpActionBar()
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
