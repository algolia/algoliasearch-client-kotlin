package serialize

import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multiple_index.BatchOperationIndex
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyIndexName
import indexA
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBatchOperationIndex : TestSerializer<BatchOperationIndex>(
    BatchOperationIndex
) {

    override val items = listOf(
        BatchOperationIndex(indexA, BatchOperation.DeleteIndex) to json {
            KeyAction to KeyDelete
            KeyIndexName to indexA.raw
        }
    )
}