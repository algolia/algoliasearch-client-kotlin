package serialize

import com.algolia.search.model.common.TaskIndex
import com.algolia.search.response.ResponseBatches
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.toTaskID
import indexA
import indexB
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestResponseBatches : TestSerializer<ResponseBatches>(ResponseBatches) {

    private val taskIndexA = TaskIndex(indexA, 0L.toTaskID())
    private val taskIndexB = TaskIndex(indexB, 1L.toTaskID())

    override val items = listOf(
        ResponseBatches(
            listOf(taskIndexA, taskIndexB)
        ) to json {
            KeyTaskID to json {
                taskIndexA.indexName.raw to taskIndexA.taskID.raw
                taskIndexB.indexName.raw to taskIndexB.taskID.raw
            }
        }
    )
}