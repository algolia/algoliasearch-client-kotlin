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
    fun snippet1() {
        runBlocking {
            client.listClusters()
        }
    }
}
