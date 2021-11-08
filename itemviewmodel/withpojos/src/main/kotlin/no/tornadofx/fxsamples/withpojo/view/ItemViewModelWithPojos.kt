package no.tornadofx.fxsamples.withpojo.view

import no.tornadofx.fxsamples.withpojo.controller.MainController
import no.tornadofx.fxsamples.withpojo.model.Category
import no.tornadofx.fxsamples.withpojo.model.Entry
import tornadofx.*

class ItemViewModelWithPojos : View("2 itemViewModels with POJO's") {
    private val categoryListView: CategoryListView by inject()
    private val centerView: CenterView by inject()
    override val root = borderpane {
        left = categoryListView.root
        center = centerView.root

    }
}

class CenterView : View() {
    private val entryView: EntryView by inject()
    private val entryDetailView: EntryDetailView by inject()
    override val root = vbox {
        add(entryView.root)
        add(entryDetailView.root)
    }
}

class EntryDetailView : View() {
    private val controller: MainController by inject()
    override val root = form {
        fieldset("entry detail information") {
            field("title") {
                id = "title"
                label(controller.entryModel.title)
            }
            field("synopsis") {
                id = "synopsis"
                label(controller.entryModel.synopsis) {
                    prefWidth = 300.0
                }
            }
            field("author") {
                id = "author"
                label(controller.entryModel.author)
            }
        }
    }
}

class EntryView : View() {
    private val controller: MainController by inject()
    override val root = tableview<Entry> {
        id = "entriesList"
        readonlyColumn("title", Entry::title).weightedWidth(1.0)
        readonlyColumn("synopsis", Entry::synopsis).weightedWidth(4.0)
        columnResizePolicy = SmartResize.POLICY
        controller.categoryModel.itemProperty.onChange {
            items.setAll(controller.entries[it!!.index])
        }
        bindSelected(controller.entryModel)
    }


}

class CategoryListView : View() {
    private val controller: MainController by inject()
    override val root = listview<Category> {
        id = "categoryList"
        prefWidth = 100.0
        items = controller.categories.asObservable()

        cellFormat { text = it.title }
        bindSelected(controller.categoryModel)
    }

}
