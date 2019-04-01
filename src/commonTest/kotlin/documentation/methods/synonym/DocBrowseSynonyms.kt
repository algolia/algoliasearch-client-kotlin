package documentation.methods.synonym

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocBrowseSynonyms {

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