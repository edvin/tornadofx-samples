package no.tornado.fxsample.workspace

import tornadofx.ItemViewModel
import tornadofx.observable

/**
 * Created by miguelius on 04/09/2017.
 */
data class Document(var title : String = "", var text: String = "")

class DocumentViewModel(var document: Document = Document()) : ItemViewModel<Document>() {
    val title = bind { document.observable(Document::title) }
    val text = bind { document.observable(Document::text) }

}