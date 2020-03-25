package com.algolia.search.model

import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * Type of logs to retrieve when performing a [com.algolia.search.endpoint.EndpointAdvanced.getLogs] operation.
 */
@Serializable(LogType.Companion::class)
sealed class LogType(override val raw: String) : Raw<String> {

    /**
     * Retrieve all the logs.
     */
    object All : LogType(KeyAll)

    /**
     * Retrieve only the queries.
     */
    object Query : LogType(KeyQuery)

    /**
     * Retrieve only the build operations.
     */
    object Build : LogType(KeyBuild)

    /**
     * Retrieve only the errors.
     */
    object Error : LogType(KeyError)

    data class Other(override val raw: String) : LogType(raw)

    companion object : KSerializer<LogType> {

        private val serializer = StringSerializer

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
