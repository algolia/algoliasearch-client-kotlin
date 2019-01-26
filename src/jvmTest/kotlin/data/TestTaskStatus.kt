package data

import com.algolia.search.saas.model.TaskStatus.NotPublished
import com.algolia.search.saas.model.TaskStatus.Published
import com.algolia.search.saas.serialize.KeyNotPublished
import com.algolia.search.saas.serialize.KeyPublished
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