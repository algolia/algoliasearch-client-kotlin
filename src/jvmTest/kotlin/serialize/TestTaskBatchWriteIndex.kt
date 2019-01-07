package serialize

import com.algolia.search.saas.data.TaskBatchWriteIndex
import com.algolia.search.saas.data.TaskIndex
import com.algolia.search.saas.serialize.KeyTaskId
import com.algolia.search.saas.toTaskID
import indexA
import indexB
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestTaskBatchWriteIndex : TestSerializer<TaskBatchWriteIndex>(TaskBatchWriteIndex) {

    private val taskIndexA = TaskIndex(indexA, 0L.toTaskID())
    private val taskIndexB = TaskIndex(indexB, 1L.toTaskID())

    override val items = listOf(
        TaskBatchWriteIndex(
            listOf(taskIndexA, taskIndexB)
        ) to json {
            KeyTaskId to json {
                taskIndexA.indexName.raw to taskIndexA.taskID.raw
                taskIndexB.indexName.raw to taskIndexB.taskID.raw
            }
        }
    )
}