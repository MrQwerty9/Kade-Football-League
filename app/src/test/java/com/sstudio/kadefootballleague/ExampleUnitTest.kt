package com.sstudio.kadefootballleague

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val stamp = "2021-01-09T15:15:00+00:00"
        val dfZone = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ")
        val date = SimpleDateFormat.getDateInstance(3, Locale("id"))
        val time = SimpleDateFormat.getTimeInstance(3, Locale("id"))
        val dZulu: Date = dfZone.parse("$stamp")
//
        System.out.println(dfZone.format(dZulu)) // uses UTC
//
        dfZone.setTimeZone(TimeZone.getDefault())
        System.out.println(date.format(dZulu)) // uses local time zone
//
//
//        dfZone.setTimeZone(TimeZone.getDefault())
//        System.out.println(time.format(dZulu)) // uses West Coast zone
//        assertEquals(4, 2 + 2)
    }
}
