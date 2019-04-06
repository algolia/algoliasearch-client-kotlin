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
                    +Desc("boosted")
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
                    +Desc("retweets")
                    +Desc("likes")
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
                    +Desc("pageviews")
                    +Desc("comments")
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
                    +Desc("rounded_pageviews")
                    +Desc("comments")
                }
            }

            index.setSettings(settings)
        }
    }
}