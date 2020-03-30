package documentation.parameters.languages

import com.algolia.search.dsl.settings
import com.algolia.search.model.search.Language
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
