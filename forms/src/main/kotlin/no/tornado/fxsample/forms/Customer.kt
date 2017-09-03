package no.tornado.fxsample.forms

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate

class Customer {
    var name by property<String>()
    fun nameProperty() = getProperty(Customer::name)

    var birthday by property<LocalDate>()
    fun birthdayProperty() = getProperty(Customer::birthday)

    var street by property<String>()
    fun streetProperty() = getProperty(Customer::street)

    var zip by property<String>()
    fun zipProperty() = getProperty(Customer::zip)

    var city by property<String>()
    fun cityProperty() = getProperty(Customer::city)

    override fun toString() = name
}
