package no.tornado.fxsample.workspace

import tornadofx.*

/**
 * Created by miguelius on 04/09/2017.
 */
data class Document(var title : String = "", var text: String = "")

class DocumentViewModel(private var document: Document = Document()) : ItemViewModel<Document>() {
    val title = bind { document.observable(Document::title) } as BindingAwareSimpleStringProperty
    val text = bind { document.observable(Document::text) } as BindingAwareSimpleStringProperty

}