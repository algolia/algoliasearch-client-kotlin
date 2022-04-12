package documentation.methods.synonym

import com.algolia.search.exception.AlgoliaApiException
import documentation.index
import documentation.indexName
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking
import shouldFailWith

@Ignore
internal class DocCopySynonyms {

//    suspend fun [Index](#method-param-indexnamesrc).copySynonyms(
//        [destination](#method-param-indexnamedest) __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        shouldFailWith<AlgoliaApiException> {
            runBlocking {
                index.copySynonyms(indexName)
            }
        }
    }
}
