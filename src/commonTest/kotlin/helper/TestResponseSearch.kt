package helper

import com.algolia.search.helper.deserialize
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import kotlin.test.Test

class TestResponseSearch {

    @Test
    fun parse() {
        @Serializable
        data class Example(val key: String)

        val response = ResponseSearch(
            hitsOrNull = listOf(
                ResponseSearch.Hit(buildJsonObject { put("key", "value") }),
                ResponseSearch.Hit(buildJsonObject { put("key", "value") })
            )
        )

        val examples = response.hits.deserialize(Example.serializer())

        examples shouldEqual listOf(
            Example("value"),
            Example("value")
        )
    }
}
