package client

import com.algolia.search.saas.model.IndexQuery
import com.algolia.search.saas.model.ObjectID
import com.algolia.search.saas.model.search.Query
import com.algolia.search.saas.model.RequestObjects
import com.algolia.search.saas.serialize.KeyObjectID
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.content
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldNotBeEmpty
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

            objects[0]!!.jsonObject[KeyObjectID].content shouldEqual objectID1
            objects[1]!!.jsonObject[KeyObjectID].content shouldEqual objectID2
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

            search.shouldNotBeEmpty()
            search.forEach {
                it.index.shouldNotBeNull()
            }
        }
    }
}