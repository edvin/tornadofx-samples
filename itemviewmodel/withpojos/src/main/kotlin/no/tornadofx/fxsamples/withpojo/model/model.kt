package no.tornadofx.fxsamples.withpojo.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Category(val title: String, val index: Int)

class CategoryModel() : ItemViewModel<Category>() {
    val title = bind { SimpleStringProperty(item?.title ?: "") }
    val index = bind { SimpleIntegerProperty(item?.index ?: 0) }
}

data class Entry(val title: String, val synopsis: String, val author: String)

class EntryModel() : ItemViewModel<Entry>() {
    val title = bind { SimpleStringProperty(item?.title ?: "") }
    val synopsis = bind { SimpleStringProperty(item?.synopsis ?: "") }
    val author = bind { SimpleStringProperty(item?.author ?: "") }
}
