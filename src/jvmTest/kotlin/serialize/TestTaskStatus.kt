package serialize

import com.algolia.search.saas.data.TaskStatus
import com.algolia.search.saas.serialize.KeyNotPublished
import com.algolia.search.saas.serialize.KeyPublished
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTaskStatus : TestSerializer<TaskStatus>(TaskStatus) {

    override val item = listOf(
        TaskStatus.Published to JsonPrimitive(KeyPublished),
        TaskStatus.NotPublished to JsonPrimitive(KeyNotPublished),
        TaskStatus.Unknown(unknown) to JsonPrimitive(unknown)
    )
}