package no.tornadofx.fxsamples.withpojo.view

import no.tornadofx.fxsamples.withpojo.controller.MainController
import no.tornadofx.fxsamples.withpojo.model.Category
import no.tornadofx.fxsamples.withpojo.model.Entry
import tornadofx.*

class itemViewModelWithPojos : View("2 itemViewModels with POJO's") {
    val categoryListView: CategoryListView by inject()
    val centerView: CenterView by inject()
    override val root = borderpane {
        left = categoryListView.root
        center = centerView.root

    }
}

class CenterView : View() {
    val entryView: EntryView by inject()
    val entryDetailView: EntryDetailView by inject()
    override val root = vbox {
        add(entryView.root)
        add(entryDetailView.root)
    }
}

class EntryDetailView() : View() {
    val controller: MainController by inject()
    override val root = form {
        fieldset("entry detail information") {
            field("title") { label(controller.entryModel.title) }
            field("synopsis") {
                label(controller.entryModel.synopsis) {
                    prefWidth = 300.0
                }
            }
            field("author") { label(controller.entryModel.author) }
        }
    }
}

class EntryView : View() {
    val controller: MainController by inject()
    override val root = tableview<Entry> {
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
    val controller: MainController by inject()
    override val root = listview<Category> {
        prefWidth = 100.0
        items = controller.categories.observable()

        cellFormat { text = it.title }
        bindSelected(controller.categoryModel)
    }

}
