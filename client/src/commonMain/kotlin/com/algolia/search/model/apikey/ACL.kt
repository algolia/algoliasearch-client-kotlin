package com.algolia.search.model.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Permission associated to an [APIKey].
 */
@Serializable(ACL.Companion::class)
public sealed class ACL(override val raw: String) : Raw<String> {

    /**
     * Allows search.
     */
    public object Search : ACL(Key.Search)

    /**
     * Allows retrieval of all index contents via the browse API.
     */
    public object Browse : ACL(Key.Browse)

    /**
     * Allows adding/updating an object in the index. (Copying/moving indices are also allowed with this permission.)
     */
    public object AddObject : ACL(Key.AddObject)

    /**
     *  Allows deleting an existing object.
     */
    public object DeleteObject : ACL(Key.DeleteObject)

    /**
     * Allows deleting index content.
     */
    public object DeleteIndex : ACL(Key.DeleteIndex)

    /**
     * Allows getting index settings.
     */
    public object Settings : ACL(Key.Settings)

    /**
     * Allows changing index settings.
     */
    public object EditSettings : ACL(Key.EditSettings)

    /**
     * Allows retrieval of analytics through the analytics API.
     */
    public object Analytics : ACL(Key.Analytics)

    /**
     * Allows listing all accessible indices.
     */
    public object ListIndices : ACL(Key.ListIndexes)

    /**
     * Allows getting the logs.
     */
    public object Logs : ACL(Key.Logs)

    /**
     *  Disables the [com.algolia.search.model.settings.Settings.unretrievableAttributes] feature for all operations
     *  returning records.
     */
    public object SeeUnretrievableAttributes : ACL(Key.SeeUnretrievableAttributes)

    public data class Other(override val raw: String) : ACL(raw)

    @Serializer(ACL::class)
    @OptIn(ExperimentalSerializationApi::class)
    public companion object : KSerializer<ACL> {

        private val serializer = String.serializer()

        override fun serialize(encoder: Encoder, value: ACL) {
            return serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ACL {
            return when (val string = String.serializer().deserialize(decoder)) {
                Key.Search -> Search
                Key.Browse -> Browse
                Key.AddObject -> AddObject
                Key.DeleteObject -> DeleteObject
                Key.DeleteIndex -> DeleteIndex
                Key.Settings -> Settings
                Key.EditSettings -> EditSettings
                Key.Analytics -> Analytics
                Key.ListIndexes -> ListIndices
                Key.Logs -> Logs
                Key.SeeUnretrievableAttributes -> SeeUnretrievableAttributes
                else -> Other(string)
            }
        }
    }
}
