package suite

import com.algolia.search.model.enums.BooleanOrQueryLanguages
import com.algolia.search.model.enums.QueryLanguage
import com.algolia.search.model.enums.TypoTolerance
import com.algolia.search.model.settings.Distinct
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
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

    @Before
    fun clean() {
        cleanIndex(suffix)
    }

    lateinit var settings : Settings

    @Before
    fun loadSettings() {
        val json = Json(encodeDefaults = false, indented = true, indent = "  ")
        val string = loadScratch("suite_settings.json").readText()
        settings = json.parse(Settings.serializer(), string)
        val serialized = json.stringify(Settings.serializer(), settings)

        serialized shouldEqual string
    }

    @Test
    fun suite() {
        runBlocking {
            val index = clientAdmin1.getIndex(indexName)

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