package model

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.model.ClientDate
import org.junit.Test
import shouldEqual
import java.util.Date

internal class TestClientDateJVM {

    private val timestamp = 10000L
    private val format = DateISO8601.format(timestamp)
    private val formatMillis = DateISO8601.format(timestamp, true)
    private val date = Date(timestamp)

    @Test
    fun raw() {
        ClientDate(format).date shouldEqual date
        ClientDate(formatMillis).date shouldEqual date
        ClientDate(timestamp).date shouldEqual date
    }
}
