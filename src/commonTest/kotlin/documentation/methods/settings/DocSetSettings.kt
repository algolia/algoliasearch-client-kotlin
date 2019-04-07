package documentation.methods.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterium
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSetSettings {

//    suspend fun Index.setSettings(
//        #{settings}: __Settings__,
//        resetToDefault: __List<SettingsKey>__ = listOf(),
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            val name = Attribute("name")
            val address = Attribute("address")
            val followers = Attribute("followers")
            val settings = Settings(
                searchableAttributes = listOf(
                    SearchableAttribute.Default(name),
                    SearchableAttribute.Default(address)
                ),
                customRanking = listOf(
                    CustomRankingCriterium.Desc(followers)
                )
            )

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}