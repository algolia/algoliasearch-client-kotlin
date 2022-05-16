package serialize.settings

import attributeA
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.AttributeForFaceting.Default
import com.algolia.search.model.settings.AttributeForFaceting.FilterOnly
import com.algolia.search.model.settings.AttributeForFaceting.Searchable
import com.algolia.search.serialize.internal.KeyFilterOnly
import com.algolia.search.serialize.internal.KeySearchable
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestAttributeForFaceting : TestSerializer<AttributeForFaceting>(AttributeForFaceting) {

    override val items = listOf(
        FilterOnly(attributeA) to JsonPrimitive("$KeyFilterOnly($attributeA)"),
        Searchable(attributeA) to JsonPrimitive("$KeySearchable($attributeA)"),
        Default(attributeA) to JsonPrimitive(attributeA.raw)
    )
}
