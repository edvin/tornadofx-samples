package no.tornadofx.fxsamples.withfxproperties.views

import no.tornadofx.fxsamples.withfxproperties.controller.PersonController
import no.tornadofx.fxsamples.withfxproperties.model.Person
import tornadofx.*

class PersonList : View() {
    val controller: PersonController by inject()

    override val root = tableview(controller.persons) {
        id = "personList"
        column("Id", Person::idProperty)
        column("Name", Person::nameProperty)
        controller.selectedPerson.rebindOnChange(this) { selectedPerson ->
            item = selectedPerson ?: Person()
        }
        smartResize()
    }
}
