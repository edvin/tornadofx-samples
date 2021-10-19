package no.tornadofx.fxsamples.withpojo.view

import javafx.scene.control.TableCell
import no.tornado.fxsample.TestBase
import no.tornadofx.fxsamples.withpojo.controller.MainController
import no.tornadofx.fxsamples.withpojo.model.Category
import no.tornadofx.fxsamples.withpojo.model.Entry
import org.testfx.api.FxAssert.verifyThat
import org.testfx.matcher.base.NodeMatchers
import org.testfx.matcher.control.TableViewMatchers
import tornadofx.*
import kotlin.test.Test

class TestMainView : TestBase() {
    override fun initView() {
        showView<ItemViewModelWithPojos>()
    }

    @Test
    fun testView() {
        val controller = find<MainController>()
        val categoryList = lookup("#categoryList").queryListView<Category>()
        val entriesList = lookup("#entriesList").queryTableView<Entry>()

        for(index in listOf(0, 2)) {
            val selectedCategory = controller.categories[index]

            clickOn(from(categoryList).lookup(selectedCategory.title).query<TableCell<String, String>>())

            verifyThat("#entriesList", TableViewMatchers.hasNumRows(controller.entries[selectedCategory.index].size))

            val selectedEntry = controller.entries[selectedCategory.index][0]

            clickOn(from(entriesList).lookup(selectedEntry.title).query<TableCell<String, String>>())

            verifyThat(lookup("#author"), NodeMatchers.hasChild(selectedEntry.author))
            verifyThat(lookup("#synopsis"), NodeMatchers.hasChild(selectedEntry.synopsis))
            verifyThat(lookup("#title"), NodeMatchers.hasChild(selectedEntry.title))
        }
    }
}