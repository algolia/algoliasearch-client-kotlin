package client

import client.data.Settings
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestClientAdvanced {

    @Test
    fun getTask() {
        runBlocking {
            val task = index.setSettings(Settings())

            println(index.getTask(task.taskID))
        }
    }
}