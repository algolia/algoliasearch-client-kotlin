package suite

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import kotlin.test.Test

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
