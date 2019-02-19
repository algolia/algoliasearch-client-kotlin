package data

import com.algolia.search.model.analytics.ABTestStatus
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
        ABTestStatus.Expired.raw to KeyExpired
        ABTestStatus.Stopped.raw to KeyStopped
        ABTestStatus.Active.raw to KeyActive
        ABTestStatus.Failed.raw to KeyFailed
        ABTestStatus.Other(unknown).raw to unknown
    }
}