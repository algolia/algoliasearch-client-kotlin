package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonPrimitive

@Serializable(TypoTolerance.Companion::class)
public sealed class TypoTolerance(override val raw: String) : Raw<String> {

    /**
     * Typo tolerance is enabled and all records matching with or without typos are retrieved
     */
    public object True : TypoTolerance(true.toString())

    /**
     * Typo tolerance is entirely disabled. Only records matching without typos are retrieved.
     */
    public object False : TypoTolerance(false.toString())

    /**
     * Retrieve records with the smallest number of typos.
     * For example, if the smallest number of typos found is 0, then only records matching without typos will be
     * returned. If the smallest number of typos found is 1, then only records matching with 1 typo will be returned.
     */
    public object Min : TypoTolerance(Key.Min)

    /**
     * Retrieve records with the 2 smallest number of typos.
     * For example, if the smallest number of typos found is 0, then only records matching with 0 or 1 typo will be
     * returned. If the smallest number of typos found is 1, then only records matching with 1 or 2 typos will be
     * returned.
     * Strict changes the engine’s ranking, forcing the [RankingCriterion.Typo] to go the top of the ranking formula.
     */
    public object Strict : TypoTolerance(Key.Strict)

    public data class Other(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(TypoTolerance::class)
    public companion object : KSerializer<TypoTolerance> {

        override fun serialize(encoder: Encoder, value: TypoTolerance) {
            when (value) {
                is True -> Boolean.serializer().serialize(encoder, true)
                is False -> Boolean.serializer().serialize(encoder, false)
                else -> String.serializer().serialize(encoder, value.raw)
            }
        }

        override fun deserialize(decoder: Decoder): TypoTolerance {
            val element = decoder.asJsonInput()

            return when {
                element.jsonPrimitive.booleanOrNull != null -> if (element.jsonPrimitive.boolean) True else False
                element.jsonPrimitive.content == Key.Min -> Min
                element.jsonPrimitive.content == Key.Strict -> Strict
                else -> Other(element.jsonPrimitive.content)
            }
        }
    }
}
