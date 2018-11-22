package com.twitsplit.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitsplit.R
import com.twitsplit.databinding.FragmentAddTweetBinding
import com.twitsplit.viewmodel.AddTweetModel

class AddTweetFragment : Fragment() {
    companion object {
        val TAG = AddTweetFragment::class.simpleName!!
    }

    private lateinit var mBinding: FragmentAddTweetBinding
    private lateinit var mModel: AddTweetModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_tweet, container, false)
        activity?.let {
            val save = it.findViewById<FloatingActionButton>(R.id.save_tweet)
            save.setOnClickListener {
                if (mModel.saveTweet())
                    activity!!.finish()
            }
        }
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            val factory = AddTweetModel.Factory(it.application)
            mModel = ViewModelProviders.of(this, factory).get(AddTweetModel::class.java)
            mBinding.viewmodel = mModel
        }
    }
}