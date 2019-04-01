package documentation.parameters.languages

import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocKeepDiacriticsOnCharacters : TestDocumentation() {

//    keepDiacriticsOnCharacters: String = "øé"

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                keepDiacriticsOnCharacters = "øé"
            }

            index.setSettings(settings)
        }
    }
}