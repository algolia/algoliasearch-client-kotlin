package documentation.methods.synonym

import com.algolia.search.exception.AlgoliaApiException
import documentation.index
import documentation.indexName
import runBlocking
import shouldFailWith
import kotlin.test.Ignore
import kotlin.test.Test

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
