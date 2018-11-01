package client.query


sealed class AroundRadius {

    object All : AroundRadius() {

        const val raw = "all"
    }

    data class InMeters(val int: kotlin.Int) : AroundRadius()
}