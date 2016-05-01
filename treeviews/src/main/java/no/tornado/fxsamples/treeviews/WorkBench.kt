package no.tornado.fxsamples.treeviews

import tornadofx.App
import tornadofx.importStylesheet

class Workbench : App() {
    override val primaryView = Treeview::class

    init {
        importStylesheet(Styles::class)
    }

}
