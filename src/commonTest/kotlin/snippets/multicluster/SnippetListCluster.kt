package snippets.multicluster

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetListCluster {

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