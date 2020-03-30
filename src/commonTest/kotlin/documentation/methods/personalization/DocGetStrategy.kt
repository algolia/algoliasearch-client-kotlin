package documentation.methods.personalization

import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
