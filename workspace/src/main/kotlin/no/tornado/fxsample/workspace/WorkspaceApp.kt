package no.tornado.fxsample.workspace

import javafx.application.Application
import tornadofx.App
import tornadofx.importStylesheet


class WorkspaceApp : App() {
    override val primaryView = DemoWorkspace::class

    init {
        importStylesheet(Styles::class)
    }

}

fun main(args: Array<String>) {
    Application.launch(WorkspaceApp::class.java, *args)
}
