package com.twitsplit

import com.twitsplit.util.StringUtils
import org.junit.Assert.*
import org.junit.Test

class StringUtilsTest {
    @Test
    @Throws(Exception::class)
    fun test1() {
        val text = "I can't believe Tweeter now"
        val result = StringUtils.getSplitTweet(text)
        assertEquals(result, text)
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        val text = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val result = StringUtils.getSplitTweet(text)
        assertFalse(result == text)
    }

    @Test
    @Throws(Exception::class)
    fun test3() {
        val text = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself."
        val result = StringUtils.getSplitTweet(text)
        assertTrue(result == "")
    }

    @Test
    @Throws(Exception::class)
    fun test4() {
        val text = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        val result = StringUtils.getSplitTweet(text)
        assertTrue(result.contains("\n"))
    }
}