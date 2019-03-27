package serialize.search

import attributeA
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.json.json
import serialize.serializer.TestKSerializerHierarchy
import shouldEqual
import kotlin.test.Test


internal class TestHierarchy {

    @Test
    fun hierarchy() {
        val hit = ResponseSearch.Hit(json { attributeA.raw to TestKSerializerHierarchy.json })

        hit.getHierarchy(attributeA) shouldEqual TestKSerializerHierarchy.item
    }
}