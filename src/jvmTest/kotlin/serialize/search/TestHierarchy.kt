package serialize.search

import attributeA
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.serializer.TestKSerializerHierarchy
import shouldEqual


@RunWith(JUnit4::class)
internal class TestHierarchy {

    @Test
    fun hierarchy() {
        val hit = ResponseSearch.Hit(json { attributeA.raw to TestKSerializerHierarchy.json })

        hit.getAsHierarchy(attributeA) shouldEqual TestKSerializerHierarchy.item
    }
}