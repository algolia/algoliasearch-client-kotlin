package client

import com.algolia.search.saas.model.settings.Settings
import com.algolia.search.saas.model.TaskStatus
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


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

    @Test
    fun copySettings() {
        runBlocking {
            index.apply {
                copyIndex(indexCopyA.indexName).wait() shouldEqual TaskStatus.Published
                copySettings(indexCopyA.indexName).wait() shouldEqual TaskStatus.Published
                getSettings() shouldEqual indexCopyA.getSettings()
                indexCopyA.apply {
                    deleteIndex().wait() shouldEqual TaskStatus.Published
                }
            }
        }
    }
}