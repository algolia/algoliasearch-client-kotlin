package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(Scope.Companion::class)
sealed class Scope(override val raw: String) : RawString {

    object Settings : Scope(KeySettings)

    object Synonyms : Scope(KeySynonyms)

    object Rules : Scope(KeyRules)

    data class Unknown(override val raw: String) : Scope(raw)

    @Serializer(Scope::class)
    companion object : KSerializer<Scope> {

        override fun serialize(output: Encoder, obj: Scope) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): Scope {
            val element = input.asJsonInput() as JsonLiteral

            return when (element.content) {
                KeySettings -> Settings
                KeySynonyms -> Synonyms
                KeyRules -> Rules
                else -> Unknown(element.content)
            }
        }
    }
}