package no.tornado.fxsample.workspace

import javafx.beans.property.SimpleMapProperty
import tornadofx.Controller
import tornadofx.Fragment
import tornadofx.observable
import java.io.File
import java.nio.charset.Charset

class EditorController : Controller() {

    val quotes = File(javaClass.getResource("quotes.txt").toURI()).readLines(Charset.forName("UTF-8"))

    val editorModelList = mutableListOf<TextEditorFragment>().observable()

    fun newEditor(): TextEditorFragment {
        val newFile = DocumentViewModel()
        newFile.title.value = "New file ${editorModelList.size}"
        val editor = TextEditorFragment(newFile)
        editorModelList.add(editor)
        return editor
    }

    fun quote(): String = quotes[(Math.random() * quotes.size).toInt()]


}
