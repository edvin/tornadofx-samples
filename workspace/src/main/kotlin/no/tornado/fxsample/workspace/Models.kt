package no.tornado.fxsample.workspace

import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

data class Document(var title: String, var text: String)

class DocumentViewModel : ItemViewModel<Document>() {
    val title = bind { SimpleStringProperty(item?.title ?: "") }
    val text = bind { SimpleStringProperty(item?.text ?: "") }
    override fun onCommit() {
        super.onCommit()

        item.text = this.text.value
        item.title = this.title.value

    }
}