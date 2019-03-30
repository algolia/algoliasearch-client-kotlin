package snippets.parameters.ranking

import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetRanking : TestSnippets() {

//    ranking {
//        // the `asc` and `desc` modifiers must be placed at the top
//        // if you are configuring an index for sorting purposes only
//        +("attribute1" modify [Asc](#parameter-option-asc))
//        +("attribute2" modify [Desc](#parameter-option-desc))
//        +[Typo](#parameter-option-typo)
//        +[Geo](#parameter-option-geo)
//        +[Words](#parameter-option-words)
//        +[Filters](#parameter-option-filters)
//        +[Proximity](#parameter-option-proximity)
//        +[Attribute](#parameter-option-attribute)
//        +[Exact](#parameter-option-exact)
//        +[Custom](#parameter-option-custom)
//    }

    @Test
    fun defaultRanking() {
        runBlocking {
            val settings = settings {
                ranking {
                    +Typo
                    +Geo
                    +Words
                    +Filters
                    +Proximity
                    +Attribute
                    +Exact
                    +Custom
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun ascending() {
        runBlocking {
            val settings = settings {
                ranking {
                    +("price" modify Asc)
                    +Typo
                    +Geo
                    +Words
                    +Filters
                    +Proximity
                    +Attribute
                    +Exact
                    +Custom
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun descending() {
        runBlocking {
            val settings = settings {
                ranking {
                    +("price" modify Desc)
                    +Typo
                    +Geo
                    +Words
                    +Filters
                    +Proximity
                    +Attribute
                    +Exact
                    +Custom
                }
            }

            index.setSettings(settings)
        }
    }
}