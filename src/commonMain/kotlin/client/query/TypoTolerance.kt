package client.query

import kotlinx.serialization.Serializable


@Serializable
sealed class TypoTolerance {

    @Serializable
    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance()

    @Serializable
    object Min : TypoTolerance() {

        const val raw = "min"
    }

    @Serializable
    object Strict : TypoTolerance() {

        const val raw = "strict"
    }
}