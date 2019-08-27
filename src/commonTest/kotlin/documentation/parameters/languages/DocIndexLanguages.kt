package documentation.parameters.languages

import com.algolia.search.dsl.camelCaseAttributes
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.Language
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocIndexLanguages {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                indexLanguages = listOf(Language.Japanese)
            }

            index.setSettings(settings)
        }
    }
}