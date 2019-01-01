package serialize

import com.algolia.search.saas.data.TaskInfo
import com.algolia.search.saas.data.TaskStatus
import com.algolia.search.saas.serialize.KeyPublished
import com.algolia.search.saas.serialize.KeyStatus
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestTaskInfo : TestSerializer<TaskInfo>(TaskInfo.serializer()) {

    override val item = listOf(
        TaskInfo(TaskStatus.Published) to json {
            KeyStatus to KeyPublished
        }
    )
}