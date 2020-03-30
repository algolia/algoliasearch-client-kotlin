package documentation.methods.multicluster

import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
