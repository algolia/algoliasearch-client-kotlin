# DSL

Extensive DSL coverage is provided to encourage a declarative style which retains the benefits of compile time safety while being more lightweight.

## Query

All attributes in the `Query` class are covered.

```kotlin
val query = query { 
   attributesToRetrieve { 
       +"color"
       +"category"
   }
}
```

Other type of queries are covered as well.

```kotlin
val deleteByQuery = deleteByQuery {
   insideBoundingBox {
      +BoundingBox(
          Point(46.650828100116044f, 7.123046875f),
          Point(45.17210966999772f, 1.009765625f)
      )
   }
}
```

## Filters

Use the DSL to generate complex filters.
The DSL constraints what you can write, so you'll never be able to write an invalid filter.

Example for the [filters](https://www.algolia.com/doc/api-reference/api-parameters/filters/) parameter.

```kotlin
val query = query {
   filters {
       and {
           facet("color", "red")
           facet("category", "shirt")
       }
       orNumeric {
           range("price", 0 until 10)
           comparison("price", Equals, 15)
       }
   }
}
```

Example for the [optionalFilters](https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/) parameter

```kotlin
  val query = query("query") {
      optionalFilters {
          and {
              facet("category", "Book")
              facet("author", "John Doe")
          }
      }
  }
```

## Settings

The Settings DSL is similar to the Query DSL.

```kotlin
val settings = settings {
   attributesToSnippet {
       +"content"(10)
   }
   searchableAttributes {
       +"name"
       +"address"
   }
   customRanking {
       +Desc("followers")
   }
}
```

## Query rule

```kotlin
val director = Attribute("director")
val rules = rules {
    rule(
        "director_rule",
        Condition(Contains, Facet(director)),
        Consequence(
            automaticFacetFilters { +director },
            edits = edits { +"director" }
        )
    )
}
```
