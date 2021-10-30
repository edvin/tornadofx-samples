package no.tornadofx.fxsample.springexample

import no.tornado.fxsample.TestBase
import kotlin.test.Test
import org.testfx.assertions.api.Assertions.assertThat

class TestSpringExampleView: TestBase() {
    override fun initView() {
        showView<SpringExampleView, SpringExampleApp>()
    }

    @Test
    fun testView() {
        assertThat(lookup(HelloBean.data).queryLabeled()).isVisible
    }
}