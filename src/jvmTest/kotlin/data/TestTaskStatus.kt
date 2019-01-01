package data

import client.data.TaskStatus.NotPublished
import client.data.TaskStatus.Published
import client.serialize.KeyNotPublished
import client.serialize.KeyPublished
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