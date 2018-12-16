package client.query


enum class MultipleQueriesStrategy(val raw: String) {
    None("none"),
    StopIfEnoughMatches("stopIfEnoughMatches")
}