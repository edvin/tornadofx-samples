package no.tornadofx.fxsamples.withfxproperties.views

import javafx.scene.control.TableView
import no.tornadofx.fxsamples.withfxproperties.controller.PersonController
import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.*

class PersonEditor : View() {
    val controller: PersonController by inject()
    var numbersTable: TableView<PhoneNumber> by singleAssign()

    override val root = form {
        fieldset("Personal Information") {
            field("Name") {
                textfield(controller.selectedPerson.name)
            }
            button("Save") {
                setOnAction {
                    controller.selectedPerson.commit()
                }
            }
        }
        fieldset("Phone Numbers") {
            vbox(5.0) {
                tableview<PhoneNumber> {
                    numbersTable = this
                    isEditable = true
                    smartResize()
                    column("Country code", PhoneNumber::countryCodeProperty).makeEditable()
                    column("Number", PhoneNumber::numberProperty).makeEditable()
                    itemsProperty().bind(controller.selectedPerson.phoneNumbers)
                }
                button("Add number") {
                    setOnAction {
                        val newNumber = PhoneNumber("", "")
                        controller.selectedPerson.phoneNumbers.value.add(newNumber)
                        numbersTable.selectionModel.select(newNumber)
                        numbersTable.edit(numbersTable.items.size - 1, numbersTable.columns.first())
                    }
                }
            }
        }
    }
}
