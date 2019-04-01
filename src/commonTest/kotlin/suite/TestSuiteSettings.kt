package suite

import clientAdmin1
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.search.RemoveStopWords
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.settings.Distinct
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.TaskStatus
import runBlocking
import shouldEqual
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class TestSuiteSettings {

    private val suffix = "settings"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            val settings = load(Settings.serializer(), "settings.json")

            index.apply {
                setSettings(settings).wait() shouldEqual TaskStatus.Published
                getSettings() shouldEqual settings

                val copy = settings.copy(
                    typoTolerance = TypoTolerance.Min,
                    ignorePlurals = IgnorePlurals.QueryLanguages(QueryLanguage.English, QueryLanguage.French),
                    removeStopWords = RemoveStopWords.QueryLanguages(QueryLanguage.English, QueryLanguage.French),
                    distinct = Distinct(1)
                )
                setSettings(copy).wait() shouldEqual TaskStatus.Published
                getSettings() shouldEqual copy
            }
        }
    }
}