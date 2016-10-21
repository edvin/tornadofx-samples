package no.tornadofx.fxsamples.withfxproperties.controller

import javafx.collections.FXCollections
import no.tornadofx.fxsamples.withfxproperties.model.Person
import no.tornadofx.fxsamples.withfxproperties.model.PersonModel
import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.Controller

class PersonController : Controller() {
    val persons = FXCollections.observableArrayList<Person>()
    val selectedPerson = PersonModel()

    init {
        // Add some test persons for the demo
        persons.add(Person(42, "John Doe", listOf(PhoneNumber("47", "33349700"), PhoneNumber("47", "333943222"))))
        persons.add(Person(43, "Jane Doe", listOf(PhoneNumber("1", "312 213 21"))))
    }
}