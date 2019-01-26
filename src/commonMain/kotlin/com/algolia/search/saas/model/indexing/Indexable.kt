package com.algolia.search.saas.model.indexing

import com.algolia.search.saas.model.ObjectID


interface Indexable {

    val objectID: ObjectID
}