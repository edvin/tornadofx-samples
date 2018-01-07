package no.tornado.fxsample.treeviews

import javafx.scene.control.TreeItem
import javafx.scene.layout.HBox
import tornadofx.*

class DemoTreeViews : View() {
    override val root = HBox()

    init {
        with(root) {
            addClass(Styles.wrapper)
            vbox {
                this += label("Based on parent-child relationships")
                treeview<Group> {
                    root = TreeItem(group)
                    root.isExpanded = true
                    cellFormat { text = it.name }
                    onUserSelect {
                        println(it)
                    }
                    populate {
                        it.value.children
                    }
                }
            }
            vbox {
                label("based on a list")
                val departments: List<Department> = persons
                        .distinctBy { it.department }
                        .map { Department(it.department) }

                treeview<PersonTreeItem>(TreeItem(TreeRoot)) {
                    cellFormat { text = it.name }

                    onUserSelect { println(it) }

                    populate { parent ->
                        when (parent.value) {
                            TreeRoot -> departments
                            is Department -> persons.filter { it.department == parent.value.name }
                            is Person -> emptyList()
                        }
                    }
                }
            }
        }
    }
}
