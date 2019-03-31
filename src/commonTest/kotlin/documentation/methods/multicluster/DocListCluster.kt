package documentation.methods.multicluster

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.client
import kotlin.test.Test


internal class DocListCluster {

//    suspend fun listClusters(
//        requestOptions: RequestOptions? = null
//    ): ResponseListClusters

    @Test
    fun listCluster() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.listClusters()
            }
        }
    }
}