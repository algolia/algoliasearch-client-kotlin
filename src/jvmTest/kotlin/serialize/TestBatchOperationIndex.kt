package serialize

import com.algolia.search.saas.data.BatchOperation
import com.algolia.search.saas.data.BatchOperationIndex
import com.algolia.search.saas.serialize.KeyAction
import com.algolia.search.saas.serialize.KeyDelete
import com.algolia.search.saas.serialize.KeyIndexName
import indexA
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBatchOperationIndex: TestSerializer<BatchOperationIndex>(BatchOperationIndex) {

    override val items = listOf(
        BatchOperationIndex(indexA, BatchOperation.DeleteIndex) to json {
            KeyAction to KeyDelete
            KeyIndexName to indexA.raw
        }
    )
}