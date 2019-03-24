package model.personalization

import com.algolia.search.model.personalization.EventType.*
import com.algolia.search.serialize.KeyClick
import com.algolia.search.serialize.KeyConversion
import com.algolia.search.serialize.KeyView
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestEventType {

    @Test
    fun raw() {
        View.raw shouldEqual KeyView
        Click.raw shouldEqual KeyClick
        Conversion.raw shouldEqual KeyConversion
        Other(unknown).raw shouldEqual unknown
    }
}