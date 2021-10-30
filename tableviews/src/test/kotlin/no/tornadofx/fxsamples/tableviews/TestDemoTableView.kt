package no.tornadofx.fxsamples.tableviews

import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestDemoTableView : TestBase() {
    override fun initView() {
        showView<DemoTableView, TableViewApp>()
    }

    @Test
    fun testTableView() {
        assertThat(lookup("#${DemoTableView.tableViewId}").queryTableView<Pair<String, Int>>()).hasExactlyNumRows(DemoTableView.mapTableContent.size)
    }
}