package model.personalization

import com.algolia.search.model.personalization.EventType.Click
import com.algolia.search.model.personalization.EventType.Conversion
import com.algolia.search.model.personalization.EventType.Other
import com.algolia.search.model.personalization.EventType.View
import com.algolia.search.serialize.KeyClick
import com.algolia.search.serialize.KeyConversion
import com.algolia.search.serialize.KeyView
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestEventType {

    @Test
    fun raw() {
        View.raw shouldEqual KeyView
        Click.raw shouldEqual KeyClick
        Conversion.raw shouldEqual KeyConversion
        Other(unknown).raw shouldEqual unknown
    }
}
