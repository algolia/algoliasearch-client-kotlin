package com.algolia.search.model.places

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAddress
import com.algolia.search.serialize.KeyAirport
import com.algolia.search.serialize.KeyBusStop
import com.algolia.search.serialize.KeyCity
import com.algolia.search.serialize.KeyCountry
import com.algolia.search.serialize.KeyTownhall
import com.algolia.search.serialize.KeyTrainStation
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(PlaceType.Companion::class)
sealed class PlaceType(override val raw: String) : Raw<String> {

    public object City : PlaceType(KeyCity)

    public object Country : PlaceType(KeyCountry)

    public object Address : PlaceType(KeyAddress)

    public object BusStop : PlaceType(KeyBusStop)

    public object TrainStation : PlaceType(KeyTrainStation)

    public object TownHall : PlaceType(KeyTownhall)

    public object Airport : PlaceType(KeyAirport)

    public data class Other(override val raw: String) : PlaceType(raw)

    companion object : KSerializer<PlaceType> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: PlaceType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): PlaceType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyCity -> City
                KeyCountry -> Country
                KeyAddress -> Address
                KeyBusStop -> BusStop
                KeyTrainStation -> TrainStation
                KeyTownhall -> TownHall
                KeyAirport -> Airport
                else -> Other(string)
            }
        }
    }
}
