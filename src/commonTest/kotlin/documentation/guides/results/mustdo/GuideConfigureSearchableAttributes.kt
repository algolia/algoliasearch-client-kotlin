package documentation.guides.results.mustdo

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideConfigureSearchableAttributes {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"title"
                    +"director"
                    +"actor"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"movie_title,actor_name,director_name"
                    +"cast"
                    +"filmography"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +Unordered("title")
                    +Ordered("cast")
                }
            }

            index.setSettings(settings)
        }
    }
}