package client

import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.serialize.KeyObjectId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.content
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


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
            val objects = algolia.getObjects(
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID1)
                ),
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID2)
                )
            )

            objects[0].jsonObject[KeyObjectId].content shouldEqual objectID1
            objects[1].jsonObject[KeyObjectId].content shouldEqual objectID2
        }
    }
}