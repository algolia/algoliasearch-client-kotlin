package com.algolia.search.dsl

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.SearchableAttribute


@Suppress("PropertyName")
@DSLParameters
public class DSLSearchableAttributes {

    private val searchableAttributes: MutableList<SearchableAttribute> = mutableListOf()

    public val Ordered = ModifierSearchableAttribute.Ordered
    public val Unordered = ModifierSearchableAttribute.Unordered

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +SearchableAttribute.Default(this)
    }

    public operator fun SearchableAttribute.unaryPlus() {
        searchableAttributes += this
    }

    public operator fun List<Attribute>.unaryPlus() {
        searchableAttributes += SearchableAttribute.Default(this)
    }

    public infix fun String.and(attribute: String): SearchableAttribute {
        return Attribute(this) and Attribute(attribute)
    }

    public infix fun Attribute.and(attribute: Attribute): SearchableAttribute {
        return SearchableAttribute.Default(this, attribute)
    }

    public infix fun String.modify(modifier: ModifierSearchableAttribute): SearchableAttribute {
        return Attribute(this) modify modifier
    }

    public infix fun Attribute.modify(modifier: ModifierSearchableAttribute): SearchableAttribute {
        return when (modifier) {
            ModifierSearchableAttribute.Ordered -> SearchableAttribute.Ordered(this)
            ModifierSearchableAttribute.Unordered -> SearchableAttribute.Unordered(this)
        }
    }

    public fun build(): List<SearchableAttribute> {
        return searchableAttributes.toList()
    }
}