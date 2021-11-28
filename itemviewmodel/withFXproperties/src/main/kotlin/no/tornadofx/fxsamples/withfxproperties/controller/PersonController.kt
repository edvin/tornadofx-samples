package no.tornadofx.fxsamples.withfxproperties.controller

import no.tornadofx.fxsamples.withfxproperties.model.Person
import no.tornadofx.fxsamples.withfxproperties.model.PersonModel
import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.*

class PersonController : Controller() {
    var persons = listOf(
        Person(42, "John Doe", mutableListOf(PhoneNumber("47", "33349700"), PhoneNumber("47", "333943222"))),
        Person(43, "Jane Doe", mutableListOf(PhoneNumber("1", "312 213 21")))
    ).asObservable()

    val selectedPerson = PersonModel()
}