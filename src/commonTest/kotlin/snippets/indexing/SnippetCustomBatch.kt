package snippets.indexing

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.multipleindex.BatchOperationIndex
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetCustomBatch : TestSnippets() {

//    suspend fun ClientSearch.multipleBatchObjects(
//        #{operations}: __List<BatchOperationIndex>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatches

    @Test
    fun batch() {
        runBlocking {
            @Serializable
            data class Person(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val operations = listOf(
                BatchOperationIndex(
                    indexName = indexName1,
                    operation = BatchOperation.AddObject(
                        json {
                            "firstname" to "Jimmie"
                            "lastname" to "Barninger"
                        }
                    )
                ),
                BatchOperationIndex(
                    indexName = indexName1,
                    operation = BatchOperation.AddObject.from(
                        serializer = Person.serializer(),
                        data = Person("Jimmie", "Barninger", ObjectID("myID1"))
                    )
                ),
                BatchOperationIndex(
                    indexName = indexName1,
                    operation = BatchOperation.UpdateObject.from(
                        serializer = Person.serializer(),
                        data = Person("Max", "Barninger", ObjectID("myID2"))
                    )
                ),
                BatchOperationIndex(
                    indexName = indexName1,
                    operation = BatchOperation.UpdateObject.from(
                        objectID = ObjectID("myID3"),
                        partial = Partial.Update(Attribute("firstname"), "McFarway")
                    )
                ),
                BatchOperationIndex(
                    indexName = indexName1,
                    operation = BatchOperation.UpdateObject.from(
                        objectID = ObjectID("myID4"),
                        partial = Partial.Update(Attribute("firstname"), "Warren"),
                        createIfNotExists = false
                    )
                ),
                BatchOperationIndex(
                    indexName = indexName2,
                    operation = BatchOperation.DeleteObject(ObjectID("myID5"))
                )
            )
            client.multipleBatchObjects(operations)
        }
    }
}