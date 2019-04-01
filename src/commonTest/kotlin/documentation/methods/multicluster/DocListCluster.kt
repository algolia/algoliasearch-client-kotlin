package documentation.methods.multicluster

import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocListCluster {

//    suspend fun listClusters(
//        requestOptions: RequestOptions? = null
//    ): ResponseListClusters

    @Test
    fun listCluster() {
        runBlocking {
            client.listClusters()
        }
    }
}