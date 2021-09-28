package no.tornado.fxsample.forms

import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.LocalDate

class Customer(name: String? = null, birthday: String? = null, street: String? = null, zip: String? = null, city: String? = null) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    val birthdayProperty = SimpleObjectProperty<LocalDate>(LocalDate.parse(birthday))
    var birthday: LocalDate by birthdayProperty

    val streetProperty = SimpleStringProperty(this, "street", street)
    var street: String by streetProperty

    val zipProperty = SimpleStringProperty(this, "zip", zip)
    var zip: String by zipProperty

    val cityProperty = SimpleStringProperty(this, "city", city)
    var city: String by cityProperty

    override fun toString() = name
}

class CustomerModel : ItemViewModel<Customer>(Customer(birthday = LocalDate.now().toString())) {
    val name = bind(Customer::nameProperty)
    val birthday: Property<LocalDate> = bind(Customer::birthdayProperty)
    val street = bind(Customer::streetProperty)
    val zip = bind(Customer::zipProperty)
    val city = bind(Customer::cityProperty)
}

