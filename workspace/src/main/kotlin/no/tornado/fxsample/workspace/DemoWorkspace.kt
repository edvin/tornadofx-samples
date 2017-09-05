package no.tornado.fxsample.workspace

import javafx.application.Platform
import javafx.beans.binding.BooleanExpression
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.StringProperty
import javafx.scene.Parent
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TextArea
import javafx.scene.layout.Pane
import tornadofx.*
import java.io.IOException
import java.io.OutputStream
import java.io.PrintStream
import java.nio.charset.Charset
import java.util.*

class DemoWorkspace : Workspace("Editor") {
    val editorController: EditorController by inject()

    init {
        menubar {
            menu("File") {
                item("New").action {
                    //workspace.dock(mainView, true)
                    log.info("Opening text file")
                    workspace.dock(editorController.newEditor(), true)
                }
                separator()
                item("Exit").action {
                    log.info("Leaving workspace")
                    Platform.exit()
                }
            }
            menu("Window"){
                item("Close all").action {
                    editorController.editorModelList.clear()
                    workspace.dock(EmptyView(),true)
                }
                separator()
                openWindowMenuItemsAtfer()
            }
            menu("Help") {
                item("About...")
            }

        }

        add(RestProgressBar::class)
        with(bottomDrawer) {
            item( "Logs") {
                textarea {
                    addClass("consola")
                    val ps = PrintStream(TextAreaOutputStream(this))
                    System.setErr(ps)
                    System.setOut(ps)
                }

            }
        }
    }

    /**
     * this extension method allows binding the open document's fragment to menu
     */
    private fun Menu.openWindowMenuItemsAtfer() {
        editorController.editorModelList.onChange { dvm ->
            dvm.next()
            if (dvm.wasAdded()) {
                dvm.addedSubList.forEach { x ->
                    val item = MenuItem(x.title)
                    item.action {
                        workspace.dock(x, true)
                    }
                    items.add(item)
                }
            } else if (dvm.wasRemoved()) {
                dvm.removed.forEach { x ->
                    workspace.viewStack.remove(x)
                    x.close()
                    println(workspace.dockedComponent)
                    val morituri = items.takeLast(items.size - 2).filter { item -> item.text.equals(x.title) }
                    items.removeAll(morituri)
                }
            }
        }
    }

}


class TextEditorFragment(val documentViewModel: DocumentViewModel) : Fragment(){
    override val root = pane {
        title = documentViewModel.title.value
        textarea (documentViewModel.text) {
            this.prefWidthProperty().bind(this@pane.widthProperty());
            this.prefHeightProperty().bind(this@pane.heightProperty());

        }
    }

    init {
        documentViewModel.title.addListener { w, o, n ->
            this.title = n
        }
    }

    override val deletable = SimpleBooleanProperty(false)
    override val closeable = SimpleBooleanProperty( true)
    override val savable = documentViewModel.dirty
    override val refreshable = documentViewModel.dirty

    override fun onSave() {
        documentViewModel.commit()
    }

    override fun onRefresh() {
        documentViewModel.rollback()
    }
}

class TextAreaOutputStream(val textArea: TextArea): OutputStream() {

    @Throws(IOException::class)
    override fun write(b: Int) {
        throw UnsupportedOperationException()
    }

    @Throws(IOException::class)
    override fun write(b: ByteArray, off: Int, len: Int) {
        // redirects data to the text area
        textArea.appendText(String(Arrays.copyOf(b, len), Charset.defaultCharset()))
        // scrolls the text area to the end of data
        textArea.scrollTop = java.lang.Double.MAX_VALUE
    }

}

class EmptyView : View() {
    val controller: EditorController by inject()
    override val root = label(controller.quote())
}