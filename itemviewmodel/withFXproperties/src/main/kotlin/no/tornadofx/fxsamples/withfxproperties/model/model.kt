package no.tornadofx.fxsamples.withfxproperties.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class PhoneNumber(countryCode: String = "", number: String = "") {
    val countryCodeProperty = SimpleStringProperty(countryCode)
    val numberProperty = SimpleStringProperty(number)
}

class Person(id: Int = -1, name: String = "", phoneNumbers: List<PhoneNumber> = emptyList()) {
    val idProperty = SimpleIntegerProperty(id)
    val nameProperty = SimpleStringProperty(name)
    val phoneNumbersProperty = SimpleListProperty(phoneNumbers.asObservable())
}

class PersonModel : ItemViewModel<Person>() {
    val id = bind(Person::idProperty)
    val name = bind(Person::nameProperty)
    val phoneNumbers = bind(Person::phoneNumbersProperty)
}
