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

    public infix fun String.modify(modifier: Modifier): AttributeForFaceting {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: Modifier): AttributeForFaceting {
        return when (modifier) {
            Modifier.Searchable -> AttributeForFaceting.Searchable(this)
            Modifier.FilterOnly -> AttributeForFaceting.FilterOnly(this)
        }
    }

    public companion object : DSL<DSLAttributesForFaceting, List<AttributeForFaceting>> {

        override operator fun invoke(block: DSLAttributesForFaceting.() -> Unit): List<AttributeForFaceting> {
            return DSLAttributesForFaceting().apply(block).attributesForFaceting
        }
    }
}