package documentation.guides.optimize.typo

import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideTypoSquatting {

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
            val records = mutableListOf<JsonObject>()

            index.browseObjects { response ->
                records += response.hits.map {
                    val map = it.toMutableMap()
                    val nbFollowers = it.getValue("nb_followers").primitive.long

                    map["is_popular"] = JsonLiteral(nbFollowers > 1000000)
                    JsonObject(map)
                }
            }
            index.saveObjects(records)
        }
    }
}