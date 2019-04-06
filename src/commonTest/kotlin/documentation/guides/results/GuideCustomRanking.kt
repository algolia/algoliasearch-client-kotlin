package documentation.guides.results

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideCustomRanking {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("boosted" modify Desc)
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("retweets" modify Desc)
                    +("likes" modify Desc)
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("pageviews" modify Desc)
                    +("comments" modify Desc)
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet4() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("rounded_pageviews" modify Desc)
                    +("comments" modify Desc)
                }
            }

            index.setSettings(settings)
        }
    }
}