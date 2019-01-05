package serialize

import com.algolia.search.saas.data.TaskStatus
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTaskStatus : TestSerializer<TaskStatus>(TaskStatus) {

    override val items = listOf(
        TaskStatus.Published,
        TaskStatus.NotPublished,
        TaskStatus.Other(unknown)
    )
}