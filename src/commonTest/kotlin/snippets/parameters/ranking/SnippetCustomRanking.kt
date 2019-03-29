package snippets.parameters.ranking

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetCustomRanking : TestSnippets() {

    @Test
    fun parameter() {
        settings {
            customRanking {
                +("attribute1" modify Asc)
                +("attribute2" modify Desc)
            }
        }
    }

    @Test
    fun snippet() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("popularity" modify Desc)
                    +("price" modify Asc)
                }
            }

            index.setSettings(settings)
        }
    }
}