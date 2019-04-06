package documentation.guides.default

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*
import kotlinx.serialization.list
import org.junit.Ignore
import org.junit.Test
import java.io.File


@Ignore
internal class GuideQueryMiddleWord {

    @Test
    fun snippet1() {
        runBlocking {
            val string = File("products.json").readText()
            val objects = Json.parse(JsonObjectSerializer.list, string)
            val records = objects.map {
                val map = it.toMutableMap()
                val suffixes = mutableListOf<JsonElement>()
                var word = map.getValue("product_reference").content

                while (word.length > 1) {
                    word = word.substring(1)
                    suffixes += JsonLiteral(word)
                }
                map["product_reference_suffixes"] = JsonArray(suffixes)
                JsonObject(map)
            }

            index.saveObjects(records)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"name"
                    +"product_reference"
                    +"product_reference_suffixes"
                }
            }

            index.setSettings(settings)
        }
    }
}