package documentation.methods.rule

import com.algolia.search.model.ObjectID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocGetRule : TestDocumentation() {

//    suspend fun Index.getRule(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseRule

    @Test
    fun getRule() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.getRule(ObjectID("myID"))
            }
        }
    }
}