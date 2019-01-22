package serialize

import com.algolia.search.saas.data.Anchoring
import com.algolia.search.saas.data.Anchoring.*
import com.algolia.search.saas.serialize.KeyContains
import com.algolia.search.saas.serialize.KeyEndsWith
import com.algolia.search.saas.serialize.KeyIs
import com.algolia.search.saas.serialize.KeyStartsWith
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestAnchoring : TestSerializer<Anchoring>(Anchoring) {

    override val items = listOf(
        Is to JsonLiteral(KeyIs),
        EndsWith to JsonLiteral(KeyEndsWith),
        StartsWith to JsonLiteral(KeyStartsWith),
        Contains to JsonLiteral(KeyContains),
        Other(unknown) to JsonLiteral(unknown)
    )
}