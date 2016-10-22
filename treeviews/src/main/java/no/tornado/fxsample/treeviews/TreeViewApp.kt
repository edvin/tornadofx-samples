package no.tornado.fxsample.treeviews

import javafx.application.Application
import tornadofx.App
import tornadofx.importStylesheet


class TreeViewApp : App() {
    override val primaryView = DemoTreeViews::class

    init {
        importStylesheet(Styles::class)
    }

}

fun main(args: Array<String>) {
    Application.launch(TreeViewApp::class.java, *args)
}
