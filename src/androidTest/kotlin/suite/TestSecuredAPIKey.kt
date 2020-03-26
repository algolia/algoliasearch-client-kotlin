package suite

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.test.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
internal class TestSecuredAPIKey {

    private val test = TestSecuredAPIKeyTools()

    @Test
    fun expiredKey() {
        test.expiredKey()
    }

    @Test
    fun validKey() {
        test.validKey()
    }

    @Test
    fun remainingValidityParameter() {
        test.remainingValidityParameter()
    }
}
