package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Language
import kotlinx.serialization.Serializable

/**
 * Specify on which attributes in your index Algolia should apply word-splitting (“decompounding”).
 * A compound word refers to a word that is formed by combining smaller words without spacing.
 * They are called noun phrases, or nominal groups, and they are particularly present in German.
 * An example is Baumhaus, which is a contraction of Baum and Haus.
 * The goal of decompounding, regarding the previous example, is to index both Baum and Haus separately,
 * nstead of as a single word.
 */
@Serializable
public data class DecompoundedAttributes internal constructor(
    val language: Language,
    val attributes: List<Attribute>
) {

    public constructor(
        language: Language.German,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())

    public constructor(
        language: Language.Finnish,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())

    public constructor(
        language: Language.Dutch,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())
}
