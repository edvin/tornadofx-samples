package no.tornado.fxsample.forms

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.HOME
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.USER
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import no.tornado.fxsample.forms.Styles.Companion.zip
import org.controlsfx.control.Notifications
import tornadofx.*

class CustomerForm : View("Register Customer") {
    val model : CustomerModel by inject()

    override val root = form {
        fieldset("Personal Information", FontAwesomeIconView(USER)) {
            field("Name") {
                textfield(model.name).required()
            }

            field("Birthday") {
                datepicker(model.birthday)
            }
        }

        fieldset("Address", FontAwesomeIconView(HOME)) {
            field("Street") {
                textfield(model.street).required()
            }
            field("Zip / City") {
                textfield(model.zip) {
                    addClass(zip)
                    required()
                }
                textfield(model.city).required()
            }
        }

        button("Save") {
            action {
                model.commit {
                    val customer = model.item
                    Notifications.create()
                            .title("Customer saved!")
                            .text("${customer.name} was born ${customer.birthday}\nand lives in\n${customer.street}, ${customer.zip} ${customer.city}")
                            .owner(this)
                            .showInformation()
                }
            }

            enableWhen(model.valid)
        }
    }

}

