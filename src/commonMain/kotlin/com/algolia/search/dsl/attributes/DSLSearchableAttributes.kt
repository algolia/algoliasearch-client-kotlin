package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.SearchableAttribute


/**
 * DSL builder for a list of [SearchableAttribute].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLSearchableAttributes(
    private val searchableAttributes: MutableList<SearchableAttribute> = mutableListOf()
) {

    public enum class Modifier {
        Unordered
    }

    public val Unordered = Modifier.Unordered

    /**
     * Convenience method.
     * Add [this] to the list of [SearchableAttribute].
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     * Add [this] to the list of [SearchableAttribute].
     */
    public operator fun Attribute.unaryPlus() {
        +SearchableAttribute.Default(this)
    }

    /**
     * Add [this] to the list of [SearchableAttribute].
     */
    public operator fun SearchableAttribute.unaryPlus() {
        searchableAttributes += this
    }

    /**
     * Convenience method.
     * Create an [SearchableAttribute] using [this] [Modifier] to be applied on [attribute].
     */
    operator fun Modifier.invoke(attribute: String): SearchableAttribute {
        return invoke(Attribute(attribute))
    }

    /**
     * Create an [SearchableAttribute] using [this] [Modifier] to be applied on [attribute].
     */
    operator fun Modifier.invoke(attribute: Attribute): SearchableAttribute {
        return when (this) {
            Modifier.Unordered -> SearchableAttribute.Unordered(attribute)
        }
    }

    public companion object : DSL<DSLSearchableAttributes, List<SearchableAttribute>> {

        override operator fun invoke(block: DSLSearchableAttributes.() -> Unit): List<SearchableAttribute> {
            return DSLSearchableAttributes().apply(block).searchableAttributes
        }
    }
}