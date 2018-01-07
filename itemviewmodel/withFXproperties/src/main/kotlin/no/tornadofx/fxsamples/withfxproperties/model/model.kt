package no.tornadofx.fxsamples.withfxproperties.model

import javafx.collections.FXCollections
import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class PhoneNumber(countryCode: String, number: String) {
    var countryCode by property(countryCode)
    fun countryCodeProperty() = getProperty(PhoneNumber::countryCode)

    var number by property(number)
    fun numberProperty() = getProperty(PhoneNumber::number)
}

class Person(id: Int, name: String, phoneNumbers: List<PhoneNumber>) {
    var id by property(id)
    fun idProperty() = getProperty(Person::id)

    var name by property(name)
    fun nameProperty() = getProperty(Person::name)

    var phoneNumbers by property(FXCollections.observableArrayList(phoneNumbers))
    fun phoneNumbersProperty() = getProperty(Person::phoneNumbers)
}

class PersonModel : ItemViewModel<Person>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
    val phoneNumbers = bind { item?.phoneNumbersProperty() }
}
