package client.query

import kotlinx.serialization.Serializable


@Serializable
sealed class BooleanOrISOCodes {

    @Serializable
    data class Boolean(val boolean: kotlin.Boolean) : BooleanOrISOCodes()

    @Serializable
    data class ISOCodes(val isoCodes: List<String>) : BooleanOrISOCodes()
}