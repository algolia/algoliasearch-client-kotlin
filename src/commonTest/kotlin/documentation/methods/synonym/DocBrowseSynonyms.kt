package documentation.methods.synonym

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocBrowseSynonyms : TestDocumentation() {

//    suspend fun Index.browseSynonyms(
//        query: __SynonymQuery__ = SynonymQuery(),
//        requestOptions: __RequestOptions?__ = null,
//        block: __suspend (ResponseSearchSynonyms) -> Unit__
//    )

    @Test
    fun browseSynonyms() {
        runBlocking {
            index.browseSynonyms {
                println(it)
            }
        }
    }
}