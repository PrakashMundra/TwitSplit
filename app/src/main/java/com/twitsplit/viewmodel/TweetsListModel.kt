package com.twitsplit.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
import com.twitsplit.App
import com.twitsplit.data.TweetsDataRepository
import com.twitsplit.data.model.Tweet

class TweetsListModel(application: Application, private val repository: TweetsDataRepository) : AndroidViewModel(application) {
    val loading = ObservableBoolean(true)
    val isEmpty = ObservableBoolean(true)

    fun getTweetsList(): LiveData<List<Tweet>> {
        val observable = MediatorLiveData<List<Tweet>>()
        observable.value = null
        val tweetsList = repository.getTweetsList()
        observable.addSource<List<Tweet>>(tweetsList) { observable.value = it }
        return observable
    }

    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return TweetsListModel(application, (application as App).getDataRepository()) as T
        }
    }
}