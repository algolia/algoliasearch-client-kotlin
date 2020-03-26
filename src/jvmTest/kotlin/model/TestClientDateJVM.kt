package model

import com.algolia.search.helper.DateISO8601
import com.algolia.search.model.ClientDate
import java.util.Date
import org.junit.Test
import shouldEqual

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
