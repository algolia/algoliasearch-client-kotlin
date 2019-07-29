package com.algolia.search.model.places

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


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

        private val serializer = StringSerializer

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