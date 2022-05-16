package documentation.guides.optimize.intent

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.rule.rules
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideDynamicFiltering {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"allergens"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val rules = rules {
                rule(
                    "gluten-free-rule",
                    conditions {
                        +condition(Contains, Literal("gluten-free"))
                    },
                    consequence(
                        edits = edits { +"gluten-free" },
                        query = query {
                            filters { and { facet("allergens", "gluten", isNegated = true) } }
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val rules = rules {
                rule(
                    "diet-rule",
                    conditions {
                        +condition(Contains, Literal("diet"))
                    },
                    consequence(
                        edits = edits { +"diet" },
                        query = query {
                            filters {
                                orTag {
                                    tag("low-carb")
                                    tag("low-fat")
                                }
                            }
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }

    @Test
    fun snippet4() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"can_deliver_quickly"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet5() {
        runTest {
            val rules = rules {
                rule(
                    "asap-rule",
                    conditions {
                        +condition(Contains, Literal("asap"))
                    },
                    consequence(
                        edits = edits { +"asap" },
                        query = query {
                            optionalFilters {
                                and {
                                    facet("can_deliver_quickly", "true")
                                }
                            }
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }

    @Test
    fun snippet6() {
        runTest {
            val rules = rules {
                rule(
                    "cheap",
                    conditions {
                        +condition(Contains, Literal("cheap"))
                    },
                    consequence(
                        edits = edits { +"cheap" },
                        query = query {
                            filters {
                                and { comparison("price", Less, 10) }
                            }
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }
}
