package documentation.methods.synonym

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocBrowseSynonyms {

//    suspend fun Index.browseSynonyms(
//        query: __SynonymQuery__ = SynonymQuery(),
//        requestOptions: __RequestOptions?__ = null
//    ): List<ResponseSearchSynonyms>

    @Test
    fun snippet1() {
        runBlocking {
            index.browseSynonyms().forEach {
                println(it)
            }
        }
    }
}
