package serialize

import client.data.TaskStatus
import client.serialize.KeyNotPublished
import client.serialize.KeyPublished
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTaskStatus : TestSerializer<TaskStatus>(null, TaskStatus) {

    override val item = listOf(
        TaskStatus.Published to JsonPrimitive(KeyPublished),
        TaskStatus.NotPublished to JsonPrimitive(KeyNotPublished),
        TaskStatus.Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items: List<Pair<List<TaskStatus>, JsonArray>> = listOf()
}