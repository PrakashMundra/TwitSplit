package com.twitsplit.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Tweets")
data class Tweet(@PrimaryKey(autoGenerate = true) var id: Int? = null, var tweet: String)