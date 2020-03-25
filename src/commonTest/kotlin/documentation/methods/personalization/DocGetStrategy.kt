package documentation.methods.personalization

import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetStrategy {

//    suspend fun ClientSearch.getPersonalizationStrategy(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponsePersonalizationStrategy

    @Test
    fun snippet1() {
        runBlocking {
            client.getPersonalizationStrategy()
        }
    }
}
