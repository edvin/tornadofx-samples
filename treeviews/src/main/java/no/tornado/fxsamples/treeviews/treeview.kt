package no.tornado.fxsamples.treeviews

import javafx.scene.control.TreeItem
import javafx.scene.layout.HBox
import tornadofx.*

data class Group(val name: String, val children: List<Group>? = null)
data class Person(val name: String, val department: String)

val group = Group("Parent",
        listOf(
                Group("Child 1"),
                Group("Child 2"),
                Group("Child 3", listOf(
                        Group("Grand child 3.1",
                                listOf(
                                        Group("Great grandchild 3.1.1"),
                                        Group("Great grandchild 3.1.2"))))
                ),
                Group("Child 4"))
)
val persons = listOf(
        Person("Mary Hanes", "Marketing"),
        Person("Steve Folley", "Customer Service"),
        Person("John Ramsy", "IT Help Desk"),
        Person("Erlick Foyes", "Customer Service"),
        Person("Erin James", "Marketing"),
        Person("Jacob Mays", "IT Help Desk"),
        Person("Larry Cable", "Customer Service"))

fun main(args: Array<String>) {

    Workbench().main(args)
}

class Workbench : App() {
    override val primaryView = Treeview::class

    init {
        importStylesheet(Styles::class)
    }

    fun main(args: Array<String>) = launch(*args)
}

class Treeview : View() {
    override val root = HBox()

    init {
        with(root) {
            addClass(Styles.wrapper)

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
            val departments = persons
                    .map { it.department }
                    .distinct().map { Person(it, "") }

            treeview<Person> {
                root = TreeItem(Person("Departments", ""))

                cellFormat { text = it.name }

                onUserSelect { println(it) }

                populate { parent ->
                    if (parent == root) departments else persons.filter { it.department == parent.value.name }
                }
            }
        }
    }
}


class Styles : Stylesheet() {
    companion object {
        val wrapper by cssclass()
    }

    init {
        s(wrapper) {
            padding = box(10.px)
            spacing = 10.px
        }
    }
}