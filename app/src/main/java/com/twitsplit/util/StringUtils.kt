package com.twitsplit.util

class StringUtils {
    companion object {
        fun getSplitTweet(tweet: String): String {
            val tweetSplits = tweet.trim().split(" ")
            val splits = ArrayList<String>()
            var newText = ""
            for (i in 0 until tweetSplits.size) {
                val textPart = tweetSplits[i].trim()
                if (newText.length + textPart.length <= 50) {
                    newText += "$textPart "
                    if (i == tweetSplits.size - 1) {
                        splits.add(newText)
                        newText = "$textPart "
                    }
                } else {
                    splits.add(newText)
                    newText = "$textPart "
                }
            }
            newText = ""
            val splitsSize = splits.size
            if (splitsSize > 1) {
                for (i in 0 until splitsSize)
                    newText += "" + (i + 1) + "/" + splitsSize + " " + splits[i] + "\n\n"
            } else
                newText = splits[0]
            return newText.trim()
        }
    }
}