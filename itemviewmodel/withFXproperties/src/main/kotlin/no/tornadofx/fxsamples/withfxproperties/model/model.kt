package no.tornadofx.fxsamples.withfxproperties.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
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
    fun idProperty() = SimpleIntegerProperty(getProperty(Person::id).value)

    var name by property(name)
    fun nameProperty() = SimpleStringProperty(getProperty(Person::name).value)

    var phoneNumbers by property(FXCollections.observableArrayList(phoneNumbers))
    fun phoneNumbersProperty() = SimpleListProperty(getProperty(Person::phoneNumbers).value)
}

class PersonModel : ItemViewModel<Person>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
    val phoneNumbers = bind { item?.phoneNumbersProperty() }
}
