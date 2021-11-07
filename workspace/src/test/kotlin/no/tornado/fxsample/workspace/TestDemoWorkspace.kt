package no.tornado.fxsample.workspace

import javafx.scene.control.TextArea
import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestDemoWorkspace : TestBase() {
    override fun initView() {
        showView<DemoWorkspace, WorkspaceApp>()
    }

    @Test
    fun testWorkspace() {

        val textToWrite = "The quick brown fox jumps over the lazy dog"

        for(idx in 0..1) {
            clickOn("File")
            clickOn("New")
            clickOn("Window")
            assertThat(lookup("New file $idx").queryLabeled()).isVisible
            clickOn("Window") // close the menu
        }

        clickOn(lookup(".back").queryLabeled())
        assertThat(lookup("New file 0").queryLabeled()).isVisible

        clickOn(lookup(".forward").queryLabeled())
        assertThat(lookup("New file 1").queryLabeled()).isVisible

        clickOn(lookup(".text-area").queryAs<TextArea>(TextArea::class.java)).write(textToWrite)
        clickOn(lookup(".save").queryLabeled())

        clickOn(lookup(".back").queryLabeled())
        assertThat(lookup(".text-area").queryAs<TextArea>(TextArea::class.java).text).isBlank

        clickOn(lookup(".forward").queryLabeled())
        assertThat(lookup(".text-area").queryAs<TextArea>(TextArea::class.java).text).isEqualTo(textToWrite)
    }
}