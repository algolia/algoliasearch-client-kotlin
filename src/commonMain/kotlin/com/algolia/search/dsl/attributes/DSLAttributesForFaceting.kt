package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.AttributeForFaceting


@Suppress("PropertyName")
@DSLParameters
public class DSLAttributesForFaceting(
    private val attributesForFaceting: MutableList<AttributeForFaceting> = mutableListOf()
) {

    public enum class Modifier {
        FilterOnly,
        Searchable
    }

    public val FilterOnly = Modifier.FilterOnly
    public val Searchable = Modifier.Searchable

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +AttributeForFaceting.Default(this)
    }

    public operator fun AttributeForFaceting.unaryPlus() {
        attributesForFaceting += this
    }

    operator fun Modifier.invoke(attribute: String): AttributeForFaceting {
        return invoke(Attribute(attribute))
    }

    operator fun Modifier.invoke(attribute: Attribute): AttributeForFaceting {
        return when (this) {
            Modifier.Searchable -> AttributeForFaceting.Searchable(attribute)
            Modifier.FilterOnly -> AttributeForFaceting.FilterOnly(attribute)
        }
    }

    public companion object : DSL<DSLAttributesForFaceting, List<AttributeForFaceting>> {

        override operator fun invoke(block: DSLAttributesForFaceting.() -> Unit): List<AttributeForFaceting> {
            return DSLAttributesForFaceting().apply(block).attributesForFaceting
        }
    }
}