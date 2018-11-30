import client.query.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestQuery {

    @Test
    fun alternativesAsExact() {
        assertEquals("ignorePlurals", AlternativesAsExact.IgnorePlurals.raw)
        assertEquals("singleWordSynonym", AlternativesAsExact.SingleWordSynonym.raw)
        assertEquals("multiWordsSynonym", AlternativesAsExact.MultiWordsSynonym.raw)
    }

    @Test
    fun aroundRadius() {
        assertEquals("all", AroundRadius.All.raw)
    }

    @Test
    fun booleanOperator() {
        assertEquals("<", BooleanOperator.Lesser.raw)
        assertEquals("<=", BooleanOperator.LesserOrEqual.raw)
        assertEquals("=", BooleanOperator.Equals.raw)
        assertEquals("!=", BooleanOperator.NotEquals.raw)
        assertEquals(">=", BooleanOperator.GreaterOrEqual.raw)
        assertEquals(">", BooleanOperator.Greater.raw)
    }

    @Test
    fun exactOnSingleWordQuery() {
        assertEquals("attribute", ExactOnSingleWordQuery.Attribute.raw)
        assertEquals("word", ExactOnSingleWordQuery.Word.raw)
        assertEquals("none", ExactOnSingleWordQuery.None.raw)
    }

    @Test
    fun queryLanguage() {
        assertEquals("af", QueryLanguage.Afrikaans.raw)
        assertEquals("ar", QueryLanguage.Arabic.raw)
        assertEquals("az", QueryLanguage.Azeri.raw)
        assertEquals("bg", QueryLanguage.Bulgarian.raw)
        assertEquals("bn", QueryLanguage.Brunei.raw)
        assertEquals("ca", QueryLanguage.Catalan.raw)
        assertEquals("cs", QueryLanguage.Czech.raw)
        assertEquals("cy", QueryLanguage.Welsh.raw)
        assertEquals("da", QueryLanguage.Danis.raw)
        assertEquals("de", QueryLanguage.German.raw)
        assertEquals("en", QueryLanguage.English.raw)
        assertEquals("eo", QueryLanguage.Esperanto.raw)
        assertEquals("es", QueryLanguage.Spanish.raw)
        assertEquals("et", QueryLanguage.Estonian.raw)
        assertEquals("eu", QueryLanguage.Basque.raw)
        assertEquals("fi", QueryLanguage.Finnish.raw)
        assertEquals("fo", QueryLanguage.Faroese.raw)
        assertEquals("fr", QueryLanguage.French.raw)
        assertEquals("gl", QueryLanguage.Galician.raw)
        assertEquals("he", QueryLanguage.Hebrew.raw)
        assertEquals("hi", QueryLanguage.Hindi.raw)
        assertEquals("hu", QueryLanguage.Hungarian.raw)
        assertEquals("hy", QueryLanguage.Armenian.raw)
        assertEquals("id", QueryLanguage.Indonesian.raw)
        assertEquals("is", QueryLanguage.Icelandic.raw)
        assertEquals("it", QueryLanguage.Italian.raw)
        assertEquals("ja", QueryLanguage.Japanese.raw)
        assertEquals("ka", QueryLanguage.Georgian.raw)
        assertEquals("kk", QueryLanguage.Kazakh.raw)
        assertEquals("ko", QueryLanguage.Korean.raw)
        assertEquals("ky", QueryLanguage.Kyrgyz.raw)
        assertEquals("lt", QueryLanguage.Lithuanian.raw)
        assertEquals("mi", QueryLanguage.Maori.raw)
        assertEquals("mn", QueryLanguage.Mongolian.raw)
        assertEquals("mr", QueryLanguage.Marathi.raw)
        assertEquals("ms", QueryLanguage.Malay.raw)
        assertEquals("mt", QueryLanguage.Maltese.raw)
        assertEquals("nb", QueryLanguage.Norwegian.raw)
        assertEquals("nl", QueryLanguage.Dutch.raw)
        assertEquals("ns", QueryLanguage.NorthernSotho.raw)
        assertEquals("pl", QueryLanguage.Polish.raw)
        assertEquals("ps", QueryLanguage.Pashto.raw)
        assertEquals("pt", QueryLanguage.Portuguese.raw)
        assertEquals("qu", QueryLanguage.Quechua.raw)
        assertEquals("ro", QueryLanguage.Romanian.raw)
        assertEquals("ru", QueryLanguage.Russian.raw)
        assertEquals("sk", QueryLanguage.Slovak.raw)
        assertEquals("sq", QueryLanguage.Albanian.raw)
        assertEquals("sv", QueryLanguage.Swedish.raw)
        assertEquals("sw", QueryLanguage.Swahili.raw)
        assertEquals("ta", QueryLanguage.Tamil.raw)
        assertEquals("te", QueryLanguage.Telugu.raw)
        assertEquals("tl", QueryLanguage.Tagalog.raw)
        assertEquals("tn", QueryLanguage.Tswana.raw)
        assertEquals("tr", QueryLanguage.Turkish.raw)
        assertEquals("tt", QueryLanguage.Tatar.raw)
    }

    @Test
    fun queryType() {
        assertEquals("prefixLast", QueryType.PrefixLast.raw)
        assertEquals("prefixAll", QueryType.PrefixAll.raw)
        assertEquals("prefixNone", QueryType.PrefixNone.raw)
    }

    @Test
    fun removeWordIfNoResults() {
        assertEquals("none", RemoveWordIfNoResults.None.raw)
        assertEquals("lastWords", RemoveWordIfNoResults.LastWords.raw)
        assertEquals("firstWords", RemoveWordIfNoResults.FirstWords.raw)
        assertEquals("allOptional", RemoveWordIfNoResults.AllOptional.raw)
    }

    @Test
    fun responseFields() {
        assertEquals("*", ResponseFields.All.raw)
        assertEquals("aroundLatLng", ResponseFields.AroundLatLng.raw)
        assertEquals("automaticRadius", ResponseFields.AutomaticRadius.raw)
        assertEquals("exhaustiveFacetsCount", ResponseFields.ExhaustiveFacetsCount.raw)
        assertEquals("facets", ResponseFields.Facets.raw)
        assertEquals("facets_stats", ResponseFields.FacetsStats.raw)
        assertEquals("hits", ResponseFields.Hits.raw)
        assertEquals("hitsPerPage", ResponseFields.HitsPerPage.raw)
        assertEquals("index", ResponseFields.Index.raw)
        assertEquals("length", ResponseFields.Length.raw)
        assertEquals("nbHits", ResponseFields.NbHits.raw)
        assertEquals("nbPages", ResponseFields.NbPages.raw)
        assertEquals("offset", ResponseFields.Offset.raw)
        assertEquals("page", ResponseFields.Page.raw)
        assertEquals("params", ResponseFields.Params.raw)
        assertEquals("processingTimeMS", ResponseFields.ProcessingTimeMS.raw)
        assertEquals("query", ResponseFields.Query.raw)
        assertEquals("queryAfterRemoval", ResponseFields.QueryAfterRemoval.raw)
        assertEquals("userData", ResponseFields.UserData.raw)
    }

    @Test
    fun snippet() {
        assertEquals("attributeA", Snippet.Attribute("attributeA").raw)
        assertEquals("attributeA:10", Snippet.Attribute("attributeA", 10).raw)
        assertEquals("*:80", Snippet.All(80).raw)
    }

    @Test
    fun sortFacetValuesBy() {
        assertEquals("alpha", SortFacetValuesBy.Alpha.raw)
        assertEquals("count", SortFacetValuesBy.Count.raw)
    }

    @Test
    fun typoTolerance() {
        assertEquals("min", TypoTolerance.Min.raw)
        assertEquals("strict", TypoTolerance.Strict.raw)
    }
}