package serialize

import com.algolia.search.saas.data.BatchWrite
import com.algolia.search.saas.data.BatchWriteIndex
import com.algolia.search.saas.serialize.KeyAction
import com.algolia.search.saas.serialize.KeyDelete
import com.algolia.search.saas.serialize.KeyIndexName
import indexA
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBatchWriteIndex: TestSerializer<BatchWriteIndex>(BatchWriteIndex) {

    override val items = listOf(
        BatchWriteIndex(indexA, BatchWrite.DeleteIndex) to json {
            KeyAction to KeyDelete
            KeyIndexName to indexA.raw
        }
    )
}