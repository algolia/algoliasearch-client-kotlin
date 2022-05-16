package model.places

import com.algolia.search.model.places.PlaceType.Address
import com.algolia.search.model.places.PlaceType.Airport
import com.algolia.search.model.places.PlaceType.BusStop
import com.algolia.search.model.places.PlaceType.City
import com.algolia.search.model.places.PlaceType.Country
import com.algolia.search.model.places.PlaceType.Other
import com.algolia.search.model.places.PlaceType.TownHall
import com.algolia.search.model.places.PlaceType.TrainStation
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestPlaceType {

    @Test
    fun raw() {
        City.raw shouldEqual Key.City
        Country.raw shouldEqual Key.Country
        Address.raw shouldEqual Key.Address
        BusStop.raw shouldEqual Key.BusStop
        TrainStation.raw shouldEqual Key.TrainStation
        TownHall.raw shouldEqual Key.Townhall
        Airport.raw shouldEqual Key.Airport
        Other(unknown).raw shouldEqual unknown
    }
}
