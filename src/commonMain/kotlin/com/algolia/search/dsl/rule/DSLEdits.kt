package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.rule.Edit


@DSLParameters
public class DSLEdits(
    private val edits: MutableList<Edit> = mutableListOf()
) {

    public operator fun Edit.unaryPlus() {
        edits += this
    }

    public operator fun String.unaryPlus() {
        +Edit(this)
    }

    public operator fun String.invoke(delete: String): Edit {
        return Edit(this, delete)
    }

    public companion object : DSL<DSLEdits, List<Edit>> {

        override operator fun invoke(block: DSLEdits.() -> Unit): List<Edit> {
            return DSLEdits().apply(block).edits
        }
    }
}