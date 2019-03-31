package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocGetSynonym : TestDocumentation() {

//    suspend fun Index.getSynonym(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Synonym

    @Test
    fun getSynonyms() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.getSynonym(ObjectID("myID"))
            }
        }
    }
}