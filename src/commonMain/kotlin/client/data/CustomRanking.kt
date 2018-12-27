package client.data

import client.serialize.KeyAsc
import client.serialize.KeyDesc


sealed class CustomRanking(open val raw: String) {

    data class Asc(val attribute: Attribute) : CustomRanking(KeyAsc)

    data class Desc(val attribute: Attribute) : CustomRanking(KeyDesc)

    data class Unknown(override val raw: String) : CustomRanking(raw)
}