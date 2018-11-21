package com.twitsplit.ui.activity

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.twitsplit.R

abstract class BaseActivity : AppCompatActivity() {
    protected fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag).commit()
    }
}