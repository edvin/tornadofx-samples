package no.tornadofx.fxsamples.withfxproperties.views

import tornadofx.View
import tornadofx.hbox
import tornadofx.plusAssign


class MainView : View("Person Editor") {
    override val root = hbox {
        this += PersonList::class
        this += PersonEditor::class
    }
}
