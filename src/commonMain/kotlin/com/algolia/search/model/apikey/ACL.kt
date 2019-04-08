package com.algolia.search.model.apikey

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(ACL.Companion::class)
public sealed class ACL(override val raw: String) : Raw<String> {

    public object Search : ACL(KeySearch)

    public object Browse : ACL(KeyBrowse)

    public object AddObject : ACL(KeyAddObject)

    public object DeleteObject : ACL(KeyDeleteObject)

    public object DeleteIndex : ACL(KeyDeleteIndex)

    public object Settings : ACL(KeySettings)

    public object EditSettings : ACL(KeyEditSettings)

    public object Analytics : ACL(KeyAnalytics)

    public object ListIndices : ACL(KeyListIndexes)

    public object Logs : ACL(KeyLogs)

    public object SeeUnretrievableAttributes : ACL(KeySeeUnretrievableAttributes)

    public data class Other(override val raw: String) : ACL(raw)

    @Serializer(ACL::class)
    internal companion object : KSerializer<ACL> {

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