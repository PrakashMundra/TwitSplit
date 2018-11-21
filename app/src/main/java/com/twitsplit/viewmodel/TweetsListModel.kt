package com.twitsplit.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean

class TweetsListModel(application: Application) : AndroidViewModel(application) {
    val loading = ObservableBoolean(true)
    val isEmpty = ObservableBoolean(true)
}