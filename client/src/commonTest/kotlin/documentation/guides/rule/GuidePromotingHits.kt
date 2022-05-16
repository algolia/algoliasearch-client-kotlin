package documentation.guides.rule

import com.algolia.search.dsl.query
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Pattern
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore

@Ignore
class GuidePromotingHits {

    /** API **/
    fun snippet1() {
        runTest {
            val rules = rules {
                rule(
                    objectID = "Promote Harry Potter Box Set",
                    conditions = conditions {
                        condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("Harry Potter"))
                    },
                    consequence = consequence(
                        promote = promotions {
                            +ObjectID("HP-12345")(0)
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }

    /** API promote some results **/
    fun snippet2() {
        runTest {
            val rules = rules {
                rule(
                    objectID = "tomato fruit",
                    conditions = conditions {
                        condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("tomato"))
                    },
                    consequence = consequence(
                        query = query {
                            filters = "food_group:fruit"
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }

    /** API promote newest **/
    fun snippet3() {
        runTest {
            val rules = rules {
                rule(
                    objectID = "Promote-iPhone-X",
                    conditions = conditions {
                        condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("iPhone"))
                    },
                    consequence = consequence(
                        promote = promotions {
                            // objectID 'iPhone-12345' ==> iPhone X (newest release)
                            +ObjectID("iPhone-12345")(0)
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }
}
