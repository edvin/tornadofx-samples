package no.tornado.fxsample.forms

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.HOME
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.USER
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import no.tornado.fxsample.forms.Styles.Companion.zip
import org.controlsfx.control.Notifications
import tornadofx.*

class CustomerForm : View() {
    override val root = Form()

    val customer = Customer()

    init {
        title = "Register Customer"

        with (root) {
            fieldset("Personal Information", FontAwesomeIconView(USER)) {
                field("Name") {
                    textfield().bind(customer.nameProperty())
                }

                field("Birthday") {
                    datepicker().bind(customer.birthdayProperty())
                }
            }

            fieldset("Address", FontAwesomeIconView(HOME)) {
                field("Street") {
                    textfield().bind(customer.streetProperty())
                }
                field("Zip / City") {
                    textfield() {
                        addClass(zip)
                        bind(customer.zipProperty())
                    }
                    textfield().bind(customer.cityProperty())
                }
            }

            button("Save") {
                setOnAction {
                    Notifications.create()
                            .title("Customer saved!")
                            .text("${customer.name} was born ${customer.birthday}\nand lives in\n${customer.street}, ${customer.zip} ${customer.city}")
                            .owner(this)
                            .showInformation()
                }

                // Save button is disabled until every field has a value
                disableProperty().bind(customer.nameProperty().isNull.or(customer.birthdayProperty().isNull)
                        .or(customer.streetProperty().isNull).or(customer.zipProperty().isNull)
                        .or(customer.cityProperty().isNull))
            }
        }
    }

}

