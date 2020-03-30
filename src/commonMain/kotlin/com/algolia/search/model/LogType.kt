package com.algolia.search.model

import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * Type of logs to retrieve when performing a [com.algolia.search.endpoint.EndpointAdvanced.getLogs] operation.
 */
@Serializable(LogType.Companion::class)
public sealed class LogType(override val raw: String) : Raw<String> {

    /**
     * Retrieve all the logs.
     */
    public object All : LogType(KeyAll)

    /**
     * Retrieve only the queries.
     */
    public object Query : LogType(KeyQuery)

    /**
     * Retrieve only the build operations.
     */
    public object Build : LogType(KeyBuild)

    /**
     * Retrieve only the errors.
     */
    public object Error : LogType(KeyError)

    public data class Other(override val raw: String) : LogType(raw)

    companion object : KSerializer<LogType> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: LogType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): LogType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyAll -> All
                KeyQuery -> Query
                KeyBuild -> Build
                KeyError -> Error
                else -> Other(string)
            }
        }
    }
}
