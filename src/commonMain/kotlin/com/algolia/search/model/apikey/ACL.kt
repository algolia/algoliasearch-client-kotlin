package com.algolia.search.model.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAddObject
import com.algolia.search.serialize.KeyAnalytics
import com.algolia.search.serialize.KeyBrowse
import com.algolia.search.serialize.KeyDeleteIndex
import com.algolia.search.serialize.KeyDeleteObject
import com.algolia.search.serialize.KeyEditSettings
import com.algolia.search.serialize.KeyListIndexes
import com.algolia.search.serialize.KeyLogs
import com.algolia.search.serialize.KeySearch
import com.algolia.search.serialize.KeySeeUnretrievableAttributes
import com.algolia.search.serialize.KeySettings
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
    public object Search : ACL(KeySearch)

    /**
     * Allows retrieval of all index contents via the browse API.
     */
    public object Browse : ACL(KeyBrowse)

    /**
     * Allows adding/updating an object in the index. (Copying/moving indices are also allowed with this permission.)
     */
    public object AddObject : ACL(KeyAddObject)

    /**
     *  Allows deleting an existing object.
     */
    public object DeleteObject : ACL(KeyDeleteObject)

    /**
     * Allows deleting index content.
     */
    public object DeleteIndex : ACL(KeyDeleteIndex)

    /**
     * Allows getting index settings.
     */
    public object Settings : ACL(KeySettings)

    /**
     * Allows changing index settings.
     */
    public object EditSettings : ACL(KeyEditSettings)

    /**
     * Allows retrieval of analytics through the analytics API.
     */
    public object Analytics : ACL(KeyAnalytics)

    /**
     * Allows listing all accessible indices.
     */
    public object ListIndices : ACL(KeyListIndexes)

    /**
     * Allows getting the logs.
     */
    public object Logs : ACL(KeyLogs)

    /**
     *  Disables the [com.algolia.search.model.settings.Settings.unretrievableAttributes] feature for all operations
     *  returning records.
     */
    public object SeeUnretrievableAttributes : ACL(KeySeeUnretrievableAttributes)

    public data class Other(override val raw: String) : ACL(raw)

    @Serializer(ACL::class)
    public companion object : KSerializer<ACL> {

        private val serializer = String.serializer()

        override fun serialize(encoder: Encoder, value: ACL) {
            return serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ACL {
            return when (val string = String.serializer().deserialize(decoder)) {
                KeySearch -> Search
                KeyBrowse -> Browse
                KeyAddObject -> AddObject
                KeyDeleteObject -> DeleteObject
                KeyDeleteIndex -> DeleteIndex
                KeySettings -> Settings
                KeyEditSettings -> EditSettings
                KeyAnalytics -> Analytics
                KeyListIndexes -> ListIndices
                KeyLogs -> Logs
                KeySeeUnretrievableAttributes -> SeeUnretrievableAttributes
                else -> Other(string)
            }
        }
    }
}
