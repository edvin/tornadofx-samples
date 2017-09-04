package no.tornado.fxsample.treeviews

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

