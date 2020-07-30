package serialize.indexing

import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyIndexName
import indexA
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestBatchOperationIndex : TestSerializer<BatchOperationIndex>(
    BatchOperationIndex
) {

    override val items = listOf(
        BatchOperationIndex(indexA, BatchOperation.DeleteIndex) to buildJsonObject {
            put(KeyAction, KeyDelete)
            put(KeyIndexName, indexA.raw)
        }
    )
}
