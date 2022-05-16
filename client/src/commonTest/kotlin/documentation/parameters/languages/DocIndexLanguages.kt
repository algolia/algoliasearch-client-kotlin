package documentation.parameters.languages

import com.algolia.search.dsl.settings
import com.algolia.search.model.search.Language
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocIndexLanguages {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                indexLanguages = listOf(Language.Japanese)
            }

            index.setSettings(settings)
        }
    }
}
