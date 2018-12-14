import client.query.helper.*


internal fun groupMap(): GroupMap<Filter> = mutableMapOf()
internal fun set(vararg filters: Filter) = mutableSetOf(*filters)

internal val attributeA = Attribute("attributeA")
internal val attributeB = Attribute("attributeB")
internal val attributeC = Attribute("attributeC")
internal val facetA = FilterFacet(attributeA, "facetA")
internal val facetB = FilterFacet(attributeB, false)
internal val comparisonA = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
internal val comparisonB = FilterComparison(attributeB, NumericOperator.NotEquals, 10.0)
internal val rangeA = FilterRange(attributeA, 0.0, 5.0)
internal val rangeB = FilterRange(attributeB, 5.0, 10.0)
internal val tagA = FilterTag("tagA")
internal val tagB = FilterTag("tagB")
internal const val nameA = "nameA"
internal const val nameB = "nameB"
internal val groupOrA = GroupOr(nameA)
internal val groupOrB = GroupOr(nameB)
internal val groupAndA = GroupAnd(nameA)
internal val groupAndB = GroupAnd(nameB)

internal fun FilterBuilder.buildTest() = build().replace("\"", "")