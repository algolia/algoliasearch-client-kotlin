package client.data

import client.serialize.KeyNone
import client.serialize.KeyStopIfEnoughMatches
import client.serialize.readAsTree
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(MultipleQueriesStrategy.Companion::class)
sealed class MultipleQueriesStrategy(override val raw: String) : RawString {

    object None : MultipleQueriesStrategy(KeyNone)

    object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    data class Unknown(override val raw: String) : MultipleQueriesStrategy(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(MultipleQueriesStrategy::class)
    internal companion object : KSerializer<MultipleQueriesStrategy> {

        override fun serialize(output: Encoder, obj: MultipleQueriesStrategy) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): MultipleQueriesStrategy {
            val element = input.readAsTree() as JsonLiteral

            return when (val content = element.content) {
                KeyNone -> None
                KeyStopIfEnoughMatches -> StopIfEnoughMatches
                else -> Unknown(content)
            }
        }
    }
}