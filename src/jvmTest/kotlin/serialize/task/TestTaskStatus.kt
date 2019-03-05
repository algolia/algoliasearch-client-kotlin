package serialize.task

import com.algolia.search.model.task.TaskStatus
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestTaskStatus : TestSerializer<TaskStatus>(TaskStatus) {

    override val items = listOf(
        TaskStatus.Published to JsonLiteral(TaskStatus.Published.raw),
        TaskStatus.NotPublished to JsonLiteral(TaskStatus.NotPublished.raw),
        TaskStatus.Other(unknown) to JsonLiteral(unknown)
    )
}