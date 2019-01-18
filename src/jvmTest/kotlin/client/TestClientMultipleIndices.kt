package client

import com.algolia.search.saas.data.IndexQuery
import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.Query
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.serialize.KeyObjectId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.content
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestClientMultipleIndices {


    @Test
    fun listIndices() {
        runBlocking {
            algolia.listIndexes()
        }
    }

    @Test
    fun getObjects() {
        runBlocking {
            val objectID1 = "442854"
            val objectID2 = "322601"
            val objectID3 = "404"
            val requests = listOf(
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID1)
                ),
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID2)
                ),
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID3)
                )
            )
            val objects = algolia.multipleGetObjects(requests)

            objects[0]!!.jsonObject[KeyObjectId].content shouldEqual objectID1
            objects[1]!!.jsonObject[KeyObjectId].content shouldEqual objectID2
            objects[2] shouldEqual null
        }
    }

    @Test
    fun multiQueries() {
        runBlocking {
            val queries = listOf(
                IndexQuery(index.indexName, Query("a")),
                IndexQuery(index.indexName, Query("b"))
            )
            val search = algolia.multipleQueries(queries)

            search.results!!.forEach {
                it.index.shouldNotBeNull()
            }
        }
    }
}