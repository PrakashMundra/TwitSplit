package com.twitsplit.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitsplit.R
import com.twitsplit.data.model.Tweet
import com.twitsplit.databinding.FragmentTweersListBinding
import com.twitsplit.ui.adapter.TweetsListAdapter
import com.twitsplit.viewmodel.TweetsListModel

class TweetsListFragment : Fragment() {
    companion object {
        val TAG = TweetsListFragment::class.simpleName!!
    }

    private lateinit var mBinding: FragmentTweersListBinding
    private lateinit var mAdapter: TweetsListAdapter
    private lateinit var mModel: TweetsListModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tweers_list, container, false)
        mAdapter = TweetsListAdapter()
        setUpRecyclerView()
        mBinding.tweetsList.adapter = mAdapter
        return mBinding.root
    }

    private fun setUpRecyclerView() {
        context?.let {
            val manager = LinearLayoutManager(it)
            mBinding.tweetsList.layoutManager = manager
            val divider = DividerItemDecoration(it, manager.orientation)
            val drawable = ContextCompat.getDrawable(it, R.drawable.divier)
            drawable?.let { divider.setDrawable(drawable) }
            mBinding.tweetsList.addItemDecoration(divider)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            val factory = TweetsListModel.Factory(it.application)
            mModel = ViewModelProviders.of(this, factory).get(TweetsListModel::class.java)
            mBinding.viewmodel = mModel
        }
    }

    override fun onResume() {
        super.onResume()
        subscribeToModel()
    }

    private fun subscribeToModel() {
        mModel.loading.set(true)
        mModel.getTweetsList().observe(this, Observer { tweets: List<Tweet>? ->
            if (!tweets.isNullOrEmpty()) {
                mModel.isEmpty.set(false)
                mAdapter.setTweets(tweets)
            } else
                mModel.isEmpty.set(true)
            mModel.loading.set(false)
        })
    }
}