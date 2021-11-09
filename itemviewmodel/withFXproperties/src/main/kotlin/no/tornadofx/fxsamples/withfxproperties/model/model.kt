package no.tornadofx.fxsamples.withfxproperties.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class PhoneNumber(countryCode: String, number: String) {
    private var countryCode: String by property(countryCode)
    fun countryCodeProperty() = getProperty(PhoneNumber::countryCode)

    private var number: String by property(number)
    fun numberProperty() = getProperty(PhoneNumber::number)
}

class Person(id: Int, name: String, phoneNumbers: List<PhoneNumber>) {
    var id: Int by property(id)
    fun idProperty() = SimpleIntegerProperty(getProperty(Person::id).value)

    var name: String by property(name)
    fun nameProperty() = SimpleStringProperty(getProperty(Person::name).value)

    private var phoneNumbers: ObservableList<PhoneNumber> by property(FXCollections.observableArrayList(phoneNumbers))
    fun phoneNumbersProperty() = SimpleListProperty(getProperty(Person::phoneNumbers).value)
}

class PersonModel : ItemViewModel<Person>() {
    val id = bind { item?.idProperty() }
    val name = bind { item?.nameProperty() }
    val phoneNumbers = bind { item?.phoneNumbersProperty() }
}
