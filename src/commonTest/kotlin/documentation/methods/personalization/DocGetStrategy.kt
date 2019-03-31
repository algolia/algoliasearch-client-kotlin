package documentation.methods.personalization

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocGetStrategy : TestDocumentation() {

//    suspend fun ClientSearch.getPersonalizationStrategy(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponsePersonalizationStrategy

    @Test
    fun getStrategy() {
        runBlocking {
            client.getPersonalizationStrategy()
        }
    }
}