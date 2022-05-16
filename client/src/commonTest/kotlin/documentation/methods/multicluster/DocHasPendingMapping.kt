package documentation.methods.multicluster

import documentation.client
import kotlinx.coroutines.test.runTest
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
        runTest {
            client.hasPendingMapping(retrieveMapping = true)
        }
    }
}
