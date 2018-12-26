package client.data

import client.serialize.asc
import client.serialize.desc


sealed class CustomRanking(open val raw: String) {

    data class Asc(val attribute: Attribute) : CustomRanking(asc)

    data class Desc(val attribute: Attribute) : CustomRanking(desc)

    data class Unknown(override val raw: String) : CustomRanking(raw)
}