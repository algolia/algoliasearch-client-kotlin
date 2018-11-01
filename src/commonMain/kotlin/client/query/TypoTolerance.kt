package client.query


sealed class TypoTolerance {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance()

    object Min : TypoTolerance() {

        const val raw = "min"
    }

    object Strict : TypoTolerance() {

        const val raw = "strict"
    }
}