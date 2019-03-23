package snippets.indexing

import clientAdmin1
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.transport.RequestOptions
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetCustomBatch {

//    suspend fun multipleBatchObjects(
//        #{operations}: __List<BatchOperationIndex>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatches

    private val suffix = "snippet"
    private val index1 = testSuiteIndexName(suffix)
    private val client = clientAdmin1

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(client, suffix) }
    }

    @Test
    fun deleteObjectBy() {
        runBlocking {
            @Serializable
            data class Person(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val operations = listOf(
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.AddObject(
                        json {
                            "firstname" to "Jimmie"
                            "lastname" to "Barninger"
                        }
                    )
                ),
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.AddObject.from(
                        serializer = Person.serializer(),
                        data = Person("Jimmie", "Barninger", ObjectID("myID1"))
                    )
                ),
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.UpdateObject.from(
                        serializer = Person.serializer(),
                        data = Person("Max", "Barninger", ObjectID("myID2"))
                    )
                ),
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.UpdateObject.from(
                        objectID = ObjectID("myID3"),
                        partial = Partial.Update(Attribute("firstname"), "McFarway")
                    )
                ),
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.UpdateObject.from(
                        objectID = ObjectID("myID4"),
                        partial = Partial.Update(Attribute("firstname"), "Warren"),
                        createIfNotExists = false
                    )
                ),
                BatchOperationIndex(
                    indexName = index1,
                    operation = BatchOperation.DeleteObject(ObjectID("myID5"))
                )
            )
            client.multipleBatchObjects(operations)
        }
    }
}