package client.data

import client.serialize.KeyAll


sealed class AroundRadius(open val raw: String) {

    object All : AroundRadius(KeyAll)

    data class InMeters(val int: kotlin.Int) : AroundRadius(int.toString())

    data class Unknown(override val raw: String) : AroundRadius(raw)
}