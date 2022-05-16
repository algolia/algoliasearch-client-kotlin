package model.places

import com.algolia.search.model.places.PlaceType.Address
import com.algolia.search.model.places.PlaceType.Airport
import com.algolia.search.model.places.PlaceType.BusStop
import com.algolia.search.model.places.PlaceType.City
import com.algolia.search.model.places.PlaceType.Country
import com.algolia.search.model.places.PlaceType.Other
import com.algolia.search.model.places.PlaceType.TownHall
import com.algolia.search.model.places.PlaceType.TrainStation
import com.algolia.search.serialize.internal.KeyAddress
import com.algolia.search.serialize.internal.KeyAirport
import com.algolia.search.serialize.internal.KeyBusStop
import com.algolia.search.serialize.internal.KeyCity
import com.algolia.search.serialize.internal.KeyCountry
import com.algolia.search.serialize.internal.KeyTownhall
import com.algolia.search.serialize.internal.KeyTrainStation
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestPlaceType {

    @Test
    fun raw() {
        City.raw shouldEqual KeyCity
        Country.raw shouldEqual KeyCountry
        Address.raw shouldEqual KeyAddress
        BusStop.raw shouldEqual KeyBusStop
        TrainStation.raw shouldEqual KeyTrainStation
        TownHall.raw shouldEqual KeyTownhall
        Airport.raw shouldEqual KeyAirport
        Other(unknown).raw shouldEqual unknown
    }
}
