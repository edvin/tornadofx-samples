package no.tornadofx.fxsamples.withfxproperties.views

import no.tornadofx.fxsamples.withfxproperties.controller.PersonController
import no.tornadofx.fxsamples.withfxproperties.model.Person
import tornadofx.*

class PersonList : View() {
    val controller: PersonController by inject()

    override val root = tableview(controller.persons) {
        column("Id", Person::idProperty)
        column("Name", Person::nameProperty)
        bindSelected(controller.selectedPerson)
        smartResize()
    }
}
