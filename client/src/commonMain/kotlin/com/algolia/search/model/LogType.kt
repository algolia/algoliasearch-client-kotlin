package com.algolia.search.model

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Type of logs to retrieve when performing a [com.algolia.search.endpoint.EndpointAdvanced.getLogs] operation.
 */
@Serializable(LogType.Companion::class)
public sealed class LogType(override val raw: String) : Raw<String> {

    /**
     * Retrieve all the logs.
     */
    public object All : LogType(Key.All)

    /**
     * Retrieve only the queries.
     */
    public object Query : LogType(Key.Query)

    /**
     * Retrieve only the build operations.
     */
    public object Build : LogType(Key.Build)

    /**
     * Retrieve only the errors.
     */
    public object Error : LogType(Key.Error)

    public data class Other(override val raw: String) : LogType(raw)

    public companion object : KSerializer<LogType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: LogType) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): LogType {
            return when (val string = serializer.deserialize(decoder)) {
                Key.All -> All
                Key.Query -> Query
                Key.Build -> Build
                Key.Error -> Error
                else -> Other(string)
            }
        }
    }
}
