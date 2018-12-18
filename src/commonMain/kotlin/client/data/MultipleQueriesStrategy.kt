package client.data


sealed class MultipleQueriesStrategy(open val raw: String) {

    object None : MultipleQueriesStrategy("none")

    object StopIfEnoughMatches : MultipleQueriesStrategy("stopIfEnoughMatches")

    data class Unknown(override val raw: String) : MultipleQueriesStrategy(raw)
}