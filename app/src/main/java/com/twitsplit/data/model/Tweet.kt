package com.twitsplit.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.twitsplit.util.StringUtils

@Entity(tableName = "Tweets")
data class Tweet(@PrimaryKey(autoGenerate = true) var id: Int? = null, var tweet: String) {
    fun getSplitTweet(): String {
        return StringUtils.getSplitTweet(tweet)
    }
}