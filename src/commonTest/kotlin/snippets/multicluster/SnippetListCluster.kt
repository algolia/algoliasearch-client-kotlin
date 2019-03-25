package snippets.multicluster

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetListCluster {

//    suspend fun ClientSearch.listUserIDs(
//        #{page}: __Int?__ = null,
//        #{hitsPerPage}: __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListUserIDs

    @Test
    fun listCluster() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.listClusters()
            }
        }
    }
}