package documentation.methods.synonym

import com.algolia.search.exception.AlgoliaApiException
import documentation.index
import documentation.indexName
import kotlinx.coroutines.test.runTest
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
            runTest {
                index.copySynonyms(indexName)
            }
        }
    }
}
