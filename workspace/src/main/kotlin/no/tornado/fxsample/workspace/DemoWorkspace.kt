package no.tornado.fxsample.workspace

import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TextArea
import tornadofx.*
import java.io.IOException
import java.io.OutputStream
import java.io.PrintStream
import java.nio.charset.Charset
import java.util.*

/**
 * Created by miguelius on 04/09/2017.
 */
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
