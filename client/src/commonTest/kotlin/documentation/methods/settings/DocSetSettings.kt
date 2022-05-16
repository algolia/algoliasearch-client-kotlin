package documentation.methods.settings

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSetSettings {

//    suspend fun Index.setSettings(
//        #{settings}: __Settings__,
//        resetToDefault: __List<SettingsKey>__ = listOf(),
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                searchableAttributes {
                    +"name"
                    +"address"
                }
                customRanking {
                    +Desc("followers")
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val settings = settings {
                searchableAttributes {
                    +"name"
                    +"address"
                }
                customRanking {
                    +Desc("followers")
                }
            }

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}
