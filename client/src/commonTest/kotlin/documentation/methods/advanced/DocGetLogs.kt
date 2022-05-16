package documentation.methods.advanced

import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetLogs {

//    suspend fun getLogs(
//        [page](#methid-param-length): __Int?__ = null,
//        [hitsPerPage](#method-param-offset): __Int?__ = null,
//        logType: __LogType?__ = null,
//        requestOptions: RequestOptions? = null
//    ): ResponseLogs

    @Test
    fun snippet1() {
        runTest {
            client.getLogs(page = 0, hitsPerPage = 100)
        }
    }
}
