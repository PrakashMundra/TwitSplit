package com.twitsplit.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.twitsplit.data.model.Tweet

@Dao
interface TweetsDao {
    @Query("SELECT * FROM Tweets ORDER BY id DESC")
    fun getTweetsList(): LiveData<List<Tweet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweet(tweet: Tweet)
}