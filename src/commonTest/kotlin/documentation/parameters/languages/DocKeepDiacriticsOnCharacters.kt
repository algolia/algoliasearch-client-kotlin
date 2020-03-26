package documentation.parameters.languages

import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocKeepDiacriticsOnCharacters {

//    keepDiacriticsOnCharacters: String = "øé"

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                keepDiacriticsOnCharacters = "øé"
            }

            index.setSettings(settings)
        }
    }
}
