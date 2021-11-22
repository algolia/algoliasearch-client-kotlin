package helper

import com.algolia.search.helper.internal.DateISO8601
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import runTest

class TestDateISO8601 {

    @Test
    fun testConcurrency() = runTest {
        coroutineScope {
            repeat(20) {
                launch(Dispatchers.Default) {
                    DateISO8601.parse("2022-02-09T22:21:13Z", false)
                }
            }
            repeat(20) {
                launch(Dispatchers.Default) {
                    DateISO8601.parse("2022-02-09T22:21:13.333Z", true)
                }
            }
        }
    }

    @Test
    fun testParse() {
        val datetime = "2022-02-09T22:21:13Z"
        val timestamp = DateISO8601.parse(datetime)
        assertEquals(1644445273000L, timestamp)
    }

    @Test
    fun testParseMillis() {
        val datetime = "2022-02-09T22:21:13.333Z"
        val timestamp = DateISO8601.parse(datetime, true)
        assertEquals(1644445273333L, timestamp)
    }
}
