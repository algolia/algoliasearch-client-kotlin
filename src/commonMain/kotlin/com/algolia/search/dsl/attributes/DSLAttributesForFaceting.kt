package com.algolia.search.dsl.attributes

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.AttributeForFaceting

/**
 * DSL for building a [List] of [AttributeForFaceting].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLAttributesForFaceting(
    private val attributesForFaceting: MutableList<AttributeForFaceting> = mutableListOf()
) {

    public enum class Modifier {
        FilterOnly,
        Searchable
    }

    public val FilterOnly: Modifier = Modifier.FilterOnly
    public val Searchable: Modifier = Modifier.Searchable

    /**
     * Convenience method.
     */
    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    /**
     * Convenience method.
     */
    public operator fun Attribute.unaryPlus() {
        +AttributeForFaceting.Default(this)
    }

    /**
     * Add [this] to [attributesForFaceting].
     */
    public operator fun AttributeForFaceting.unaryPlus() {
        attributesForFaceting += this
    }

    /**
     * Convenience method.
     */
    public operator fun Modifier.invoke(attribute: String): AttributeForFaceting {
        return invoke(Attribute(attribute))
    }

    /**
     * Create an [AttributeForFaceting] using [this] [Modifier] to be applied on [attribute].
     */
    public operator fun Modifier.invoke(attribute: Attribute): AttributeForFaceting {
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
