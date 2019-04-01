package documentation.methods.personalization

import documentation.TestDocumentation
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
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