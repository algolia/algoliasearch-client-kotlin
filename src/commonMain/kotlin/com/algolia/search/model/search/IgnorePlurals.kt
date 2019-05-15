package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral


@Serializable(IgnorePlurals.Companion::class)
public sealed class IgnorePlurals {

    /**
     * Enables the ignore plurals functionality, where singulars and plurals are considered equivalent (foot = feet).
     * The languages supported here are either every language or those set by [Settings.queryLanguages]
     */
    public object True: IgnorePlurals()

    /**
     * Which disables ignore plurals, where singulars and plurals are not considered the same for matching purposes
     * (foot will not find feet).
     */
    public object False: IgnorePlurals()

    /**
     * A list of [QueryLanguage] for which ignoring plurals should be enabled.
     * This list of [queryLanguages] will override any values that you may have set in [Settings.queryLanguages].
     */
    public data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : IgnorePlurals() {

        public constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(IgnorePlurals::class)
    companion object : KSerializer<IgnorePlurals> {

        override fun serialize(encoder: Encoder, obj: IgnorePlurals) {
            when (obj) {
                is True -> BooleanSerializer.serialize(encoder, true)
                is False -> BooleanSerializer.serialize(encoder, false)
                is QueryLanguages -> QueryLanguage.list.serialize(encoder, obj.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): IgnorePlurals {
            return when (val element = decoder.asJsonInput()) {
                is JsonArray -> QueryLanguages(element.map {
                    JsonNonStrict.fromJson(QueryLanguage, it)
                })
                is JsonLiteral -> if (element.boolean) True else False
                else -> throw Exception()
            }
        }
    }
}