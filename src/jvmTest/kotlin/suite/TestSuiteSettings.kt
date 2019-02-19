package suite

import com.algolia.search.model.search.BooleanOrQueryLanguages
import com.algolia.search.model.search.QueryLanguage
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
    private val languages = BooleanOrQueryLanguages.QueryLanguages(QueryLanguage.English, QueryLanguage.French)
    private val index = clientAdmin1.initIndex(indexName)

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix)
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
                    ignorePlurals = languages,
                    removeStopWords = languages,
                    distinct = Distinct.True
                )
                setSettings(copy).wait() shouldEqual TaskStatus.Published
                getSettings() shouldEqual copy
            }
        }
    }
}