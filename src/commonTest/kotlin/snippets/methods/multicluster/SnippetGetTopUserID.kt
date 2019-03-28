package snippets.methods.multicluster

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetGetTopUserID {

//    suspend fun ClientSearch.getTopUserID(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseTopUserID

    @Test
    fun getTopUserID() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.getTopUserID()
            }
        }
    }
}