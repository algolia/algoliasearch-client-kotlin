package snippets.methods.synonym

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetBrowseSynonyms : TestSnippets() {

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