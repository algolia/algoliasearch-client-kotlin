package com.algolia.search.model.apikey

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(ACL.Companion::class)
sealed class ACL(override val raw: String) : Raw<String> {

    object Search : ACL(KeySearch)

    object Browse : ACL(KeyBrowse)

    object AddObject : ACL(KeyAddObject)

    object DeleteObject : ACL(KeyDeleteObject)

    object DeleteIndex : ACL(KeyDeleteIndex)

    object Settings : ACL(KeySettings)

    object EditSettings : ACL(KeyEditSettings)

    object Analytics : ACL(KeyAnalytics)

    object ListIndices : ACL(KeyListIndexes)

    object Logs : ACL(KeyLogs)

    object SeeUnretrievableAttributes : ACL(KeySeeUnretrievableAttributes)

    data class Other(override val raw: String) : ACL(raw)

    @Serializer(ACL::class)
    companion object : KSerializer<ACL> {

        private val serializer = StringSerializer

        override fun serialize(encoder: Encoder, obj: ACL) {
            return serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ACL {
            val string = StringSerializer.deserialize(decoder)

            return when (string) {
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