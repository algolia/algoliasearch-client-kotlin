package documentation.methods.multicluster

import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocHasPendingMapping {

//    suspend fun hasPendingMapping(
//        #{retrieveMapping}: __Boolean__ = false,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseHasPendingMapping

    @Test
    fun snippet1() {
        runBlocking {
            client.hasPendingMapping(retrieveMapping = true)
        }
    }
}
