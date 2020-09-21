package documentation.guides.default

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.serialize.internal.Json
import documentation.index
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Ignore
import org.junit.Test
import java.io.File

@Ignore
internal class GuideQueryMiddleWord {

    @Test
    fun snippet1() {
        runBlocking {
            val string = File("products.json").readText()
            val objects = Json.decodeFromString(ListSerializer(JsonObject.serializer()), string)
            val records = objects.map {
                val map = it.toMutableMap()
                val suffixes = mutableListOf<JsonElement>()
                var word = map.getValue("product_reference").jsonPrimitive.content

                while (word.length > 1) {
                    word = word.substring(1)
                    suffixes += JsonPrimitive(word)
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
