package documentation.guides.optimize.typo

import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideTypoSquatting {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                ranking {
                    +Desc("is_popular")
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
        runTest {
            val records = index.browseObjects().flatMap { response ->
                response.hits.map {
                    val map = it.toMutableMap()
                    val nbFollowers = it.getValue("nb_followers").jsonPrimitive.long

                    map["is_popular"] = JsonPrimitive(nbFollowers > 1000000)
                    JsonObject(map)
                }
            }

            index.saveObjects(records)
        }
    }
}
