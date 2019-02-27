package model

import com.algolia.search.helper.dateISO8601
import com.algolia.search.helper.dateISO8601Millis
import com.algolia.search.model.ClientDate
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import java.util.*


@RunWith(JUnit4::class)
internal class TestClientDate {

    private val timestamp = 10000L
    private val date = Date(timestamp)
    private val format = dateISO8601.format(date)
    private val formatMillis = dateISO8601Millis.format(date)

    @Test
    fun raw() {
        ClientDate(format).date shouldEqual date
        ClientDate(formatMillis).date shouldEqual date
        ClientDate(timestamp).date shouldEqual date
    }
}