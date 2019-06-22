package model.places

import com.algolia.search.model.places.PlaceType.*
import com.algolia.search.serialize.*
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