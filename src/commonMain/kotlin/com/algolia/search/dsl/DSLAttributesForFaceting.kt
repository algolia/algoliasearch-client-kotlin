package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.AttributeForFaceting


@Suppress("PropertyName")
@DSLParameters
public class DSLAttributesForFaceting {

    private val attributesForFaceting: MutableList<AttributeForFaceting> = mutableListOf()

    public val FilterOnly = ModifierAttributesForFaceting.FilterOnly
    public val Searchable = ModifierAttributesForFaceting.Searchable

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +AttributeForFaceting.Default(this)
    }

    public operator fun AttributeForFaceting.unaryPlus() {
        attributesForFaceting += this
    }

    public infix fun String.modify(modifier: ModifierAttributesForFaceting): AttributeForFaceting {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: ModifierAttributesForFaceting): AttributeForFaceting {
        return when (modifier) {
            ModifierAttributesForFaceting.Searchable -> AttributeForFaceting.Searchable(this)
            ModifierAttributesForFaceting.FilterOnly -> AttributeForFaceting.FilterOnly(this)
        }
    }

    public fun build(): List<AttributeForFaceting> {
        return attributesForFaceting.toList()
    }
}