package no.tornadofx.fxsamples.tableviews

import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*

class DemoTableView : View() {
    override val root = GridPane()

    init {
        with (root) {
            row {
                vbox {
                    label("Tableview from a map")
                    tableview(FXCollections.observableArrayList<Map.Entry<String, Int>>(mapTableContent.entries)) {
                        id = tableViewId
                        readonlyColumn("Item", Map.Entry<String, Int>::key)
                        readonlyColumn("Count", Map.Entry<String, Int>::value)
                        resizeColumnsToFitContent()
                    }
                }
            }
        }
    }

    companion object {
        val mapTableContent = mapOf(Pair("item 1", 5), Pair("item 2", 10), Pair("item 3", 6))
        const val tableViewId = "mapTable"
    }

}
