package client.query

import kotlinx.serialization.Serializable


@Serializable
sealed class AroundRadius {

    @Serializable
    object All : AroundRadius() {

        const val raw = "all"
    }

    @Serializable
    data class InMeters(val int: kotlin.Int) : AroundRadius()
}