package no.tornado.fxsample.workspace

import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestDemoWorkspace : TestBase() {
    override fun initView() {
        showView<DemoWorkspace, WorkspaceApp>()
    }

    @Test
    fun testWorkspace() {
        for(idx in 0..1) {
            clickOn("File")
            clickOn("New")
            clickOn("Window")
            assertThat(lookup("New file $idx").queryLabeled()).isVisible
            clickOn("Window") // close the menu
        }
    }
}