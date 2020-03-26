package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyStrict
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.content

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
    public object Min : TypoTolerance(KeyMin)

    /**
     * Retrieve records with the 2 smallest number of typos.
     * For example, if the smallest number of typos found is 0, then only records matching with 0 or 1 typo will be
     * returned. If the smallest number of typos found is 1, then only records matching with 1 or 2 typos will be
     * returned.
     * Strict changes the engine’s ranking, forcing the [RankingCriterion.Typo] to go the top of the ranking formula.
     */
    public object Strict : TypoTolerance(KeyStrict)

    public data class Other(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(TypoTolerance::class)
    companion object : KSerializer<TypoTolerance> {

        override fun serialize(encoder: Encoder, obj: TypoTolerance) {
            when (obj) {
                is True -> BooleanSerializer.serialize(encoder, true)
                is False -> BooleanSerializer.serialize(encoder, false)
                else -> StringSerializer.serialize(encoder, obj.raw)
            }
        }

        override fun deserialize(decoder: Decoder): TypoTolerance {
            val element = decoder.asJsonInput()

            return when {
                element.booleanOrNull != null -> if (element.boolean) True else False
                element.content == KeyMin -> Min
                element.content == KeyStrict -> Strict
                else -> Other(element.content)
            }
        }
    }
}
