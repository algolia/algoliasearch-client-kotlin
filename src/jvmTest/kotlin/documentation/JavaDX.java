package documentation;

import com.algolia.search.configuration.*;
import com.algolia.search.model.*;
import com.algolia.search.model.analytics.Variant;
import com.algolia.search.model.apikey.APIKeyParams;
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction;
import com.algolia.search.model.filter.Filter;
import com.algolia.search.model.filter.FilterGroup;
import com.algolia.search.model.filter.NumericOperator;
import com.algolia.search.model.indexing.BatchOperation;
import com.algolia.search.model.indexing.DeleteByQuery;
import com.algolia.search.model.insights.EventName;
import com.algolia.search.model.insights.InsightsEvent;
import com.algolia.search.model.insights.InsightsEvent.Resources;
import com.algolia.search.model.multicluster.UserIDQuery;
import com.algolia.search.model.multipleindex.IndexQuery;
import com.algolia.search.model.multipleindex.RequestObjects;
import com.algolia.search.model.rule.*;
import com.algolia.search.model.search.*;
import com.algolia.search.model.settings.NumericAttributeFilter;
import com.algolia.search.model.settings.Settings;
import com.algolia.search.model.synonym.SynonymQuery;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlinx.serialization.json.JsonObject;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.*;

public class JavaDX {
    private ApplicationID applicationID = new ApplicationID("foo");
    private APIKey apiKey = new APIKey("bar");
    private IndexName indexName = new IndexName("index");
    private Query query = new Query("query");
    private Attribute attribute = new Attribute("attribute");
    private String url = "http://example.com";
    private ObjectID objectID = new ObjectID("objectID");
    private JsonObject jsonObject = new JsonObject(new HashMap<>());
    private EventName eventName = new EventName("event");

    @AfterClass
    public static void tearDown() {
        System.out.println("Java DX rocks! \uD83D\uDC4C");
    }

    @Test
    public void construction() {
        ConfigurationAnalytics analytics = new ConfigurationAnalytics(applicationID, apiKey);
        ConfigurationInsights insights = new ConfigurationInsights(applicationID, apiKey);
        ConfigurationSearch search = new ConfigurationSearch(applicationID, apiKey);
        ConfigurationPlaces places = new ConfigurationPlaces();

        RetryableHost host = new RetryableHost(url);
        Variant variant = new Variant(indexName, 10);
        APIKeyParams params = new APIKeyParams();
        SecuredAPIKeyRestriction restriction = new SecuredAPIKeyRestriction();

        Filter.Facet facetString = new Filter.Facet(attribute, "value");
        Filter.Facet facetBoolean = new Filter.Facet(attribute, true);
        Filter.Facet facetInteger = new Filter.Facet(attribute, 42);
        Filter.Numeric numericInt = new Filter.Numeric(attribute, NumericOperator.Equals, 42);
        Filter.Numeric numericLong = new Filter.Numeric(attribute, NumericOperator.NotEquals, 42L);
        Filter.Numeric numericIntRange = new Filter.Numeric(attribute, new IntRange(0, 10));
        Filter.Numeric numericLongRange = new Filter.Numeric(attribute, new LongRange(0, 10));
        Filter.Numeric numericFloatRange = new Filter.Numeric(attribute, 0.1f, 0.2f);
        Filter.Numeric numericDoubleRange = new Filter.Numeric(attribute, 0.1d, 0.2d, true);
        Filter.Tag invalidTag = new Filter.Tag(attribute, false, "tag"); //FIXME: Internal constructor still visible
        Filter.Tag tag = new Filter.Tag("tag");
        FilterGroup groupAnd = new FilterGroup.And.Facet(new HashSet<>(Arrays.asList(facetString, facetInteger)));
        FilterGroup groupOr = new FilterGroup.Or.Numeric(numericInt, numericLong);
        FilterGroup groupTag = new FilterGroup.Or.Tag(new HashSet<>(Collections.singleton(tag)), "tag");

        BatchOperation operation = new BatchOperation.PartialUpdateObject(objectID, jsonObject);
        DeleteByQuery deleteByQuery = new DeleteByQuery("foo:bar");

        Resources.ObjectIDs objectIDs = new Resources.ObjectIDs(Collections.singletonList(objectID));
        List<Integer> positions = Arrays.asList(0, 1);
        InsightsEvent eventView = new InsightsEvent.View(eventName, indexName, objectIDs);
        InsightsEvent eventClick = new InsightsEvent.Click(eventName, indexName, positions);
        InsightsEvent eventConversion = new InsightsEvent.Conversion(eventName, indexName, objectIDs);

        UserIDQuery userIDQuery = new UserIDQuery();
        IndexQuery indexQuery = new IndexQuery(indexName);
        RequestObjects objects = new RequestObjects(indexName, objectID);
        AutomaticFacetFilters automaticFacetFilters = new AutomaticFacetFilters(attribute);
        Condition condition = new Condition(Anchoring.Is.INSTANCE,  //TODO: Refactor to avoid needing .INSTANCE
                new Pattern.Facet(attribute));
        Consequence consequence = new Consequence(Collections.singletonList(automaticFacetFilters));

        Edit edit = new Edit("delete");
        Rule rule = new Rule(objectID, condition, consequence);
        RuleQuery ruleQuery = new RuleQuery();
        Facet facet = new Facet("foo", 42);
        HighlightResult highlightResult = new HighlightResult("value",
                MatchLevel.Full.INSTANCE, //TODO: Refactor to avoid needing .INSTANCE
                Collections.emptyList());
        MatchedGeoLocation matchedGeoLocation = new MatchedGeoLocation(new Point(0f, 1f));

        Query query = new Query(); // TODO: Settings.Builder for better DX
        Settings settings = new Settings(); // TODO: Settings.Builder for better DX

        RankingInfo rankingInfo = new RankingInfo(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Snippet snippet = new Snippet(attribute);
        NumericAttributeFilter numericAttributeFilter = new NumericAttributeFilter(attribute);
        SynonymQuery synonymQuery = new SynonymQuery("query");
    }

    @Test
    public void search_helper() {
//        Constructors.toIndexName("foo");
        new APIKey("foo");
    }
}
