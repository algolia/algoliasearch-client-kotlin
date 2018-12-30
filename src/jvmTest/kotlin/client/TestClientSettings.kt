package client

import client.data.Settings
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestClientSettings {

    @Test
    fun getSettings() {
        runBlocking {
            val settings = index.getSettings()

            println(settings)
        }
    }

    @Test
    fun setSettings() {
        runBlocking {
            val task = index.setSettings(Settings())

            println(task)
        }
    }
}