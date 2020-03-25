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
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringSerializer

/**
 * Permission associated to an [APIKey].
 */
@Serializable(ACL.Companion::class)
sealed class ACL(override val raw: String) : Raw<String> {

    /**
     * Allows search.
     */
    object Search : ACL(KeySearch)

    /**
     * Allows retrieval of all index contents via the browse API.
     */
    object Browse : ACL(KeyBrowse)

    /**
     * Allows adding/updating an object in the index. (Copying/moving indices are also allowed with this permission.)
     */
    object AddObject : ACL(KeyAddObject)

    /**
     *  Allows deleting an existing object.
     */
    object DeleteObject : ACL(KeyDeleteObject)

    /**
     * Allows deleting index content.
     */
    object DeleteIndex : ACL(KeyDeleteIndex)

    /**
     * Allows getting index settings.
     */
    object Settings : ACL(KeySettings)

    /**
     * Allows changing index settings.
     */
    object EditSettings : ACL(KeyEditSettings)

    /**
     * Allows retrieval of analytics through the analytics API.
     */
    object Analytics : ACL(KeyAnalytics)

    /**
     * Allows listing all accessible indices.
     */
    object ListIndices : ACL(KeyListIndexes)

    /**
     * Allows getting the logs.
     */
    object Logs : ACL(KeyLogs)

    /**
     *  Disables the [com.algolia.search.model.settings.Settings.unretrievableAttributes] feature for all operations
     *  returning records.
     */
    object SeeUnretrievableAttributes : ACL(KeySeeUnretrievableAttributes)

    data class Other(override val raw: String) : ACL(raw)

    @Serializer(ACL::class)
    companion object : KSerializer<ACL> {

        private val serializer = StringSerializer

        override fun serialize(encoder: Encoder, obj: ACL) {
            return serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ACL {
            return when (val string = StringSerializer.deserialize(decoder)) {
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
