package client.data

import client.serialize.KeyMin
import client.serialize.KeyStrict


sealed class TypoTolerance(open val raw: String) {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance(KeyMin)

    object Strict : TypoTolerance(KeyStrict)

    data class Unknown(override val raw: String) : TypoTolerance(raw)
}