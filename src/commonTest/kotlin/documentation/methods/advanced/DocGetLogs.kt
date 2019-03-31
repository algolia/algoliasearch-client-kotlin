package documentation.methods.advanced

import runBlocking
import documentation.client
import kotlin.test.Test


internal class DocGetLogs {

//    suspend fun getLogs(
//        [page](#methid-param-length): __Int?__ = null,
//        [hitsPerPage](#method-param-offset): __Int?__ = null,
//        logType: __LogType?__ = null,
//        requestOptions: RequestOptions? = null
//    ): ResponseLogs

    @Test
    fun getLogs() {
        runBlocking {
            client.getLogs(page = 0, hitsPerPage = 100)
        }
    }
}