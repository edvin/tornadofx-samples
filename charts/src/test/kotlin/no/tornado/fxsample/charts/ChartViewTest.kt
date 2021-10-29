package no.tornado.fxsample.charts

import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import tornadofx.*
import kotlin.test.Test

class ChartViewTest: TestBase() {
    override fun initView() {
        showView<ChartView, ChartApp>()
    }

    @Test
    fun testMainView() {
        assertThat(testedView.root).hasExactlyChildren(8, ".chart")

        testedView.root.getChildList()?.forEach {
            assertThat(it).isVisible
            assertThat(it).hasStyleableParent(testedView.root)
        }
    }
}