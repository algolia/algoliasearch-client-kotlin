package documentation.parameters.ranking

import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocRanking {

//    ranking {
//        // the `asc` and `desc` modifiers must be placed at the top
//        // if you are configuring an index for sorting purposes only
//        +[Asc](#parameter-option-asc)("attribute1")
//        +[Desc](#parameter-option-desc)("attribute2")
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
    fun snippet1() {
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
    fun snippet2() {
        runBlocking {
            val settings = settings {
                ranking {
                    +Asc("price")
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
    fun snippet3() {
        runBlocking {
            val settings = settings {
                ranking {
                    +Desc("price")
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