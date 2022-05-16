package documentation.parameters.languages

import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocKeepDiacriticsOnCharacters {

//    keepDiacriticsOnCharacters: String = "øé"

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                keepDiacriticsOnCharacters = "øé"
            }

            index.setSettings(settings)
        }
    }
}
