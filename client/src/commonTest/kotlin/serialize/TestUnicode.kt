package serialize

import com.algolia.search.model.IndexName
import com.algolia.search.model.internal.request.RequestMultipleQueries
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import org.junit.Test
import kotlin.test.assertEquals

class TestUnicode {

    @Test
    fun pairedSurrogate() {
        val input = "Fox🦊"
        val query = RequestMultipleQueries(
            listOf(IndexQuery(IndexName("index_name"), Query(input)))
        )
        val encoded = JsonNoDefaults.encodeToString(RequestMultipleQueries.serializer(), query)
        assertEquals("""{"requests":[{"indexName":"index_name","params":"query=Fox%F0%9F%A6%8A"}]}""", encoded)
    }

    @Test
    fun unpairedSurrogate() {
        val input = "Fox🦊"
        val trunked = input.substring(0, input.length - 1)
        val query = RequestMultipleQueries(
            listOf(IndexQuery(IndexName("index_name"), Query(trunked)))
        )
        val encoded = JsonNoDefaults.encodeToString(RequestMultipleQueries.serializer(), query)
        assertEquals("""{"requests":[{"indexName":"index_name","params":"query=Fox"}]}""", encoded)
    }
}
