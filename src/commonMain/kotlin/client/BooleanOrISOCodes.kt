package client


sealed class BooleanOrISOCodes {

    class Boolean(val boolean: kotlin.Boolean) : BooleanOrISOCodes()

    class ISOCodes(val isoCodes: List<String>) : BooleanOrISOCodes()
}