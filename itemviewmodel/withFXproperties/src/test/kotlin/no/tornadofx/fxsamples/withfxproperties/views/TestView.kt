package no.tornadofx.fxsamples.withfxproperties.views

import javafx.collections.ObservableList
import javafx.scene.control.TableCell
import javafx.scene.input.KeyCode
import no.tornado.fxsample.TestBase
import no.tornadofx.fxsamples.withfxproperties.model.Person
import no.tornadofx.fxsamples.withfxproperties.WithFXPropertiesApp
import org.testfx.api.FxAssert.verifyThat
import org.testfx.matcher.control.TableViewMatchers
import org.testfx.matcher.control.TextInputControlMatchers
import tornadofx.FX.Companion.find
import kotlin.test.Test

class TestView : TestBase() {

    private lateinit var persons: ObservableList<Person>

    override fun initView() {
        showView<ItemViewModelWithFxMainView, WithFXPropertiesApp>()
        persons = find<PersonList>().controller.persons
    }

    @Test
    fun testView() {
        verifyThat("#personList", TableViewMatchers.hasNumRows(persons.size))

        var idx = 0
        persons.forEach {
            verifyThat(
                "#personList",
                TableViewMatchers.containsRowAtIndex(idx++, it.idProperty.value, it.nameProperty.value)
            )
        }

        clickOn(persons[0].nameProperty.value)
        verifyThat("#phoneNumbers", TableViewMatchers.hasNumRows(2))
        verifyThat("#infoName", TextInputControlMatchers.hasText(persons[0].nameProperty.value))

        clickOn(persons[1].nameProperty.value)
        verifyThat("#phoneNumbers", TableViewMatchers.hasNumRows(1))
        verifyThat("#infoName", TextInputControlMatchers.hasText(persons[1].nameProperty.value))
    }

    @Test
    fun testAddNumber() {
        val newRegion = "2"
        val newNumber = "624 426 42"

        clickOn(persons[1].nameProperty.value)

        clickOn("Add number")

        write(newRegion).push(KeyCode.ENTER)

        val phoneNumbersTable = lookup("#phoneNumbers").queryTableView<Person>()
        clickOn(
            from(phoneNumbersTable).lookup(".table-cell").nth(cellIndex(1, 1, 2)).query<TableCell<String, String>>()
        ).write(newNumber).push(KeyCode.ENTER)

        clickOn("Save")
        verifyThat("#phoneNumbers", TableViewMatchers.hasNumRows(2))
        assert(
            from(phoneNumbersTable).lookup(".table-cell").nth(cellIndex(1, 0, 2))
                .query<TableCell<String, String>>().text.equals(newRegion)
        )
        assert(
            from(phoneNumbersTable).lookup(".table-cell").nth(cellIndex(1, 1, 2))
                .query<TableCell<String, String>>().text.equals(newNumber)
        )
    }

    @Test
    fun testChangeName() {
        val newName = "James Smith"
        val testedPerson = persons[0]

        clickOn(testedPerson.nameProperty.value)

        clickOn("#infoName").rightClickOn().clickOn("Select All")

        write(newName).push(KeyCode.ENTER)

        clickOn("Save")

        verifyThat("#personList", TableViewMatchers.containsRowAtIndex(0, testedPerson.idProperty.value, newName))
    }

    @Test
    fun testReset() {
        val newName = "Jenny Newman"
        val testedPerson = persons[1]
        val currentName = testedPerson.nameProperty.value

        clickOn(testedPerson.nameProperty.value)

        clickOn("#infoName").rightClickOn().clickOn("Select All")

        write(newName).push(KeyCode.ENTER)

        clickOn("Reset")

        verifyThat("#personList", TableViewMatchers.containsRowAtIndex(1, testedPerson.idProperty.value, currentName))
    }
}