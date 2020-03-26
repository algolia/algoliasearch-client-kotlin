package model.places

import com.algolia.search.model.places.PlaceType.Address
import com.algolia.search.model.places.PlaceType.Airport
import com.algolia.search.model.places.PlaceType.BusStop
import com.algolia.search.model.places.PlaceType.City
import com.algolia.search.model.places.PlaceType.Country
import com.algolia.search.model.places.PlaceType.Other
import com.algolia.search.model.places.PlaceType.TownHall
import com.algolia.search.model.places.PlaceType.TrainStation
import com.algolia.search.serialize.KeyAddress
import com.algolia.search.serialize.KeyAirport
import com.algolia.search.serialize.KeyBusStop
import com.algolia.search.serialize.KeyCity
import com.algolia.search.serialize.KeyCountry
import com.algolia.search.serialize.KeyTownhall
import com.algolia.search.serialize.KeyTrainStation
import kotlin.test.Test
import shouldEqual
import unknown

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
