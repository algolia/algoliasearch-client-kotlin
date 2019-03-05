package model.analytics

import com.algolia.search.model.analytics.ABTestStatus.*
import com.algolia.search.serialize.KeyActive
import com.algolia.search.serialize.KeyExpired
import com.algolia.search.serialize.KeyFailed
import com.algolia.search.serialize.KeyStopped
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestABStatus {

    @Test
    fun raw() {
        Expired.raw to KeyExpired
        Stopped.raw to KeyStopped
        Active.raw to KeyActive
        Failed.raw to KeyFailed
        Other(unknown).raw to unknown
    }
}