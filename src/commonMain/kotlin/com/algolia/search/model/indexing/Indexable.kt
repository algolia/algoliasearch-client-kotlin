package com.algolia.search.model.indexing

import com.algolia.search.model.ObjectID


interface Indexable {

    val objectID: ObjectID
}