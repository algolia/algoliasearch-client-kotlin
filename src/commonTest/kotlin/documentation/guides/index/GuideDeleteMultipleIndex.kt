package documentation.guides.index

import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
class GuideDeleteMultipleIndex {

    @Test
    fun snippet1() {
        runBlocking {
            val indices = client.listIndices().items

            if (indices.isNotEmpty()) {
                val operations = indices.map {
                    BatchOperationIndex(it.indexName, BatchOperation.DeleteIndex)
                }

                client.multipleBatchObjects(operations)
            }
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val indices = client.listIndices().items.filter {
                it.indexName.raw.contains("_tmp")
            }

            if (indices.isNotEmpty()) {
                val operations = indices.map {
                    BatchOperationIndex(it.indexName, BatchOperation.DeleteIndex)
                }

                client.multipleBatchObjects(operations)
            }
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            client.initIndex(IndexName("YourIndexName")).deleteIndex()
        }
    }
}
