package serialize.search

import attributeA
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.json.json
import kotlin.test.Test
import serialize.serializer.TestKSerializerHierarchy
import shouldEqual


internal class TestHierarchy {

    @Test
    fun hierarchy() {
        val hit = ResponseSearch.Hit(json { attributeA.raw to TestKSerializerHierarchy.json })

        hit.getHierarchy(attributeA) shouldEqual TestKSerializerHierarchy.item
    }
}