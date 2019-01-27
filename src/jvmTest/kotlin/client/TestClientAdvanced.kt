package client

import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientAdvanced {

    @Test
    fun getTask() {
        runBlocking {
            val task = index.setSettings(Settings())

            println(index.getTask(task.taskID))
        }
    }

    @Test
    fun waits() {
        runBlocking {
            index.apply {
                setSettings(Settings()).wait() shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun waitTask() {
        runBlocking {
            val task = index.setSettings(Settings())

            index.waitTask(task.taskID) shouldEqual TaskStatus.Published
        }
    }
}