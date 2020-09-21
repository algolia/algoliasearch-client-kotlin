package serialize.task

import com.algolia.search.model.task.TaskStatus
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestTaskStatus : TestSerializer<TaskStatus>(TaskStatus) {

    override val items = listOf(
        TaskStatus.Published to JsonPrimitive(TaskStatus.Published.raw),
        TaskStatus.NotPublished to JsonPrimitive(TaskStatus.NotPublished.raw),
        TaskStatus.Other(unknown) to JsonPrimitive(unknown)
    )
}
