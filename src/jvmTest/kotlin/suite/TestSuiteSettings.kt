package suite

import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.search.RemoveStopWords
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.settings.Distinct
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteSettings {

    private val suffix = "settings"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Before
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
                    distinct = Distinct.True
                )
                setSettings(copy).wait() shouldEqual TaskStatus.Published
                getSettings() shouldEqual copy
            }
        }
    }
}