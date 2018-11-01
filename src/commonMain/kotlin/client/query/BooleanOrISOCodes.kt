package client.query


sealed class BooleanOrISOCodes {

    data class Boolean(val boolean: kotlin.Boolean) : BooleanOrISOCodes()

    data class ISOCodes(val isoCodes: List<String>) : BooleanOrISOCodes()
}