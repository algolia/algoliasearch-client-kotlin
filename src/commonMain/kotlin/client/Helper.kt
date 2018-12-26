package client

import client.data.Attribute
import client.data.Snippet


fun String.toIndex() = Index(this)

fun String.toAttribute() = Attribute(this)

infix fun Attribute.to(numberOfWords: Int?) = Snippet(this, numberOfWords)