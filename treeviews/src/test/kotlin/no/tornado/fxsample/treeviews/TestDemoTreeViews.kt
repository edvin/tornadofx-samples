package no.tornado.fxsample.treeviews

import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestDemoTreeViews : TestBase() {
    override fun initView() {
        showView<DemoTreeViews, TreeViewApp>()
    }

    @Test
    fun testMainView() {
        assertThat(lookup(DemoTreeViews.listLabel).queryLabeled()).isVisible
        assertThat(lookup(DemoTreeViews.parentChildLabel).queryLabeled()).isVisible
    }
}