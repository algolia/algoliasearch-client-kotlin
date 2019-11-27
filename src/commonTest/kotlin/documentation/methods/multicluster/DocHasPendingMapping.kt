package documentation.methods.multicluster

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.response.ResponseHasPendingMapping
import com.algolia.search.transport.RequestOptions
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