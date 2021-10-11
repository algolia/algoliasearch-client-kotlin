package helper

import com.algolia.search.helper.internal.DateISO8601
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class TestDateISO8601JVM {

    @Test
    fun test() = runBlocking {
        coroutineScope {
            repeat(20) {
                launch(Dispatchers.Default) {
                    DateISO8601.parse("2022-02-09T22:21:13Z", false)
                }
            }
        }
    }

    @Test
    fun testInMillis() = runBlocking {
        coroutineScope {
            repeat(20) {
                launch(Dispatchers.Default) {
                    DateISO8601.parse("2022-02-09T22:21:13.333Z", true)
                }
            }
        }
    }
}
