package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.SearchableAttribute


@Suppress("PropertyName")
@DSLParameters
public class DSLSearchableAttributes(
    private val searchableAttributes: MutableList<SearchableAttribute> = mutableListOf()
) {

    public enum class Modifier {
        Ordered,
        Unordered
    }

    public val Ordered = Modifier.Ordered
    public val Unordered = Modifier.Unordered

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +SearchableAttribute.Default(this)
    }

    public operator fun SearchableAttribute.unaryPlus() {
        searchableAttributes += this
    }

    operator fun Modifier.invoke(attribute: String): SearchableAttribute {
        return invoke(Attribute(attribute))
    }

    operator fun Modifier.invoke(attribute: Attribute): SearchableAttribute {
        return when (this) {
            Modifier.Ordered -> SearchableAttribute.Ordered(attribute)
            Modifier.Unordered -> SearchableAttribute.Unordered(attribute)
        }
    }

    public companion object : DSL<DSLSearchableAttributes, List<SearchableAttribute>> {

        override operator fun invoke(block: DSLSearchableAttributes.() -> Unit): List<SearchableAttribute> {
            return DSLSearchableAttributes().apply(block).searchableAttributes
        }
    }
}