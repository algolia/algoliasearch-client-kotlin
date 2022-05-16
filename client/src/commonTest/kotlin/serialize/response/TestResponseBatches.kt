package serialize.response

import com.algolia.search.helper.toTaskID
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.serialize.internal.Key
import indexA
import indexB
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestResponseBatches : TestSerializer<ResponseBatches>(ResponseBatches) {

    private val taskIndexA = TaskIndex(indexA, 0L.toTaskID())
    private val taskIndexB = TaskIndex(indexB, 1L.toTaskID())

    override val items = listOf(
        ResponseBatches(
            listOf(taskIndexA, taskIndexB)
        ) to buildJsonObject {
            put(
                Key.TaskID,
                buildJsonObject {
                    put(taskIndexA.indexName.raw, taskIndexA.taskID.raw)
                    put(taskIndexB.indexName.raw, taskIndexB.taskID.raw)
                }
            )
        }
    )
}
