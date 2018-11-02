package client.query


sealed class BooleanOrQueryLanguage {

    class Boolean(val boolean: kotlin.Boolean) : BooleanOrQueryLanguage()

    class QueryLanguages(vararg val queryLanguage: QueryLanguage) : BooleanOrQueryLanguage()
}