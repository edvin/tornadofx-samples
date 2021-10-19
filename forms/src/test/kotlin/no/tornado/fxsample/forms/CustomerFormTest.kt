package no.tornado.fxsample.forms

import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.input.KeyCode
import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import java.time.LocalDate
import kotlin.test.Test

class CustomerFormTest: TestBase() {
    override fun initView() {
        showView<CustomerForm>()
    }

    @Test
    fun testForm() {
        val saveButton = lookup("#save").query<Button>()

        clickOn("#name").write("John Smith").push(KeyCode.ENTER)
        assertThat(saveButton).isDisabled
        clickOn("#street").write("Railway Street").push(KeyCode.ENTER)
        assertThat(saveButton).isDisabled
        interact{
            lookup("#birthday").query<DatePicker>().value = LocalDate.of(1970, 1, 1)
        }
        assertThat(saveButton).isDisabled
        clickOn("#zip").write("3132").push(KeyCode.ENTER)
        assertThat(saveButton).isDisabled
        clickOn("#city").write("Ringwood").push(KeyCode.ENTER)
        assertThat(saveButton).isEnabled
    }
}