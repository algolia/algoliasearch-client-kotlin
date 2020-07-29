package com.algolia.search.model.places

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAddress
import com.algolia.search.serialize.KeyAirport
import com.algolia.search.serialize.KeyBusStop
import com.algolia.search.serialize.KeyCity
import com.algolia.search.serialize.KeyCountry
import com.algolia.search.serialize.KeyTownhall
import com.algolia.search.serialize.KeyTrainStation
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PlaceType.Companion::class)
public sealed class PlaceType(override val raw: String) : Raw<String> {

    public object City : PlaceType(KeyCity)

    public object Country : PlaceType(KeyCountry)

    public object Address : PlaceType(KeyAddress)

    public object BusStop : PlaceType(KeyBusStop)

    public object TrainStation : PlaceType(KeyTrainStation)

    public object TownHall : PlaceType(KeyTownhall)

    public object Airport : PlaceType(KeyAirport)

    public data class Other(override val raw: String) : PlaceType(raw)

    public companion object : KSerializer<PlaceType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: PlaceType) {
            serializer.serialize(encoder, value.raw)
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
