package model.task

import com.algolia.search.model.task.TaskStatus.NotPublished
import com.algolia.search.model.task.TaskStatus.Published
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyPublished
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestTaskStatus {

    @Test
    fun raw() {
        Published.raw shouldEqual KeyPublished
        NotPublished.raw shouldEqual KeyNotPublished
    }
}