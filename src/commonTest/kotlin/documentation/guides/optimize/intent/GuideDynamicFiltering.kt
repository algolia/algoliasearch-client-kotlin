package documentation.guides.optimize.intent

import com.algolia.search.dsl.*
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideDynamicFiltering {

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
            val rules = rules {
                rule(
                    "gluten-free-rule",
                    Condition(Contains, Literal("gluten-free")),
                    Consequence(
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
        runBlocking {
            val rules = rules {
                rule(
                    "diet-rule",
                    Condition(Contains, Literal("diet")),
                    Consequence(
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
        runBlocking {
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
        runBlocking {
            val rules = rules {
                rule(
                    "asap-rule",
                    Condition(Contains, Literal("asap")),
                    Consequence(
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
}