package serialize.indexing

import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.serialize.internal.Key
import indexA
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestBatchOperationIndex : TestSerializer<BatchOperationIndex>(
    BatchOperationIndex
) {

    override val items = listOf(
        BatchOperationIndex(indexA, BatchOperation.DeleteIndex) to buildJsonObject {
            put(Key.Action, Key.Delete)
            put(Key.IndexName, indexA.raw)
        }
    )
}
