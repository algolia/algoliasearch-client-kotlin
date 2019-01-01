package client.data

import client.serialize.KeyRules
import client.serialize.KeySettings
import client.serialize.KeySynonyms
import client.serialize.RawStringSerializer


sealed class Scope(override val raw: String) : RawString {

    object Settings : Scope(KeySettings)

    object Synonyms : Scope(KeySynonyms)

    object Rules : Scope(KeyRules)

    data class Unknown(override val raw: String) : Scope(raw)

    companion object : RawStringSerializer<Scope>
}