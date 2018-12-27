package client.data

import client.serialize.KeyNone
import client.serialize.KeyStopIfEnoughMatches


sealed class MultipleQueriesStrategy(open val raw: String) {

    object None : MultipleQueriesStrategy(KeyNone)

    object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    data class Unknown(override val raw: String) : MultipleQueriesStrategy(raw)
}