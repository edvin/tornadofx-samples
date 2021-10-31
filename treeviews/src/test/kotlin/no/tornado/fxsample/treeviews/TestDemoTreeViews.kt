package no.tornado.fxsample.treeviews

import javafx.scene.control.TreeCell
import javafx.scene.control.TreeView
import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestDemoTreeViews : TestBase() {
    override fun initView() {
        showView<DemoTreeViews, TreeViewApp>()
    }

    @Test
    fun testMainView() {
        assertThat(lookup(DemoTreeViews.listLabel).queryLabeled()).isVisible
        assertThat(lookup(DemoTreeViews.parentChildLabel).queryLabeled()).isVisible
    }

    @Test
    fun testChildParentView() {
        val treeView = lookup("#${DemoTreeViews.childParentView}").query<TreeView<Group>>()

        val selectedItem = group.children?.get(2)

        assertThat(treeView.getTreeItem(3).value).isEqualTo(selectedItem)

        val cell3 = lookup(selectedItem?.name).query<TreeCell<Group>>()

        assertThat(cell3).isVisible

        doubleClickOn(cell3)

        val selectedChild = selectedItem?.children?.get(0)

        val grandChild = lookup(selectedChild?.name).query<TreeCell<Group>>()

        doubleClickOn(grandChild)

        val greatGrandChild = selectedChild?.children?.get(1)

        assertThat(lookup(greatGrandChild?.name).query<TreeCell<Group>>()).isVisible
    }

    @Test
    fun testListView() {
        val listView = lookup("#${DemoTreeViews.departmentsPersonView}").query<TreeView<PersonTreeItem>>()

        assertThat(listView).isVisible

        assertThat(lookup(TreeRoot.name).query<TreeCell<PersonTreeItem>>()).isVisible

        doubleClickOn(TreeRoot.name)

        departments.forEach {
            assertThat(lookup(it.name).query<TreeCell<PersonTreeItem>>()).isVisible
        }

        val selectedDepartment = departments[1]

        doubleClickOn(selectedDepartment.name)

        persons.filter { person -> person.department == selectedDepartment.name }.forEach{
            assertThat(lookup(it.name).query<TreeCell<PersonTreeItem>>()).isVisible
        }
    }
}