package com.twitsplit

import android.app.Application
import com.twitsplit.data.TweetsDataRepository
import com.twitsplit.data.local.AppDatabase

class App : Application() {
    private fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this)!!
    }

    fun getDataRepository(): TweetsDataRepository {
        return TweetsDataRepository.getInstance(getDatabase().tweetsDao())!!
    }
}