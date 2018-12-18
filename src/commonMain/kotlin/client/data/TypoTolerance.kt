package client.data


sealed class TypoTolerance(open val raw: String) {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance("min")

    object Strict : TypoTolerance("strict")

    data class Unknown(override val raw: String) : TypoTolerance(raw)
}