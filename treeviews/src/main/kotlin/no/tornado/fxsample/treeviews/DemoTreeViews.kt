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
                val departments = persons
                        .map { it.department }
                        .distinct().map { Person(it, "") }

                treeview(TreeItem(Person("Departments", ""))) {
                    cellFormat { text = it.name }

                    onUserSelect { println(it) }

                    populate { parent ->
                        if (parent == root) departments else persons.filter { it.department == parent.value.name }
                    }
                }
            }
        }
    }
}
