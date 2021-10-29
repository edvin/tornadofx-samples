package no.tornado.fxsample

import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Stage
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import tornadofx.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class TestBase : ApplicationTest() {

    lateinit var testedView: View

    val stage: Stage = FxToolkit.registerPrimaryStage()

    inline fun <reified T: View, reified A: App> showView() {
        FxToolkit.setupApplication(A::class.java)

        testedView = find<T>()

        FxToolkit.setupFixture {
            stage.show()
        }
    }

    /*
    ** Both row and column numbers start from 0, and tableWidth is the number of columns
     */
    fun cellIndex(row: Int, column: Int, tableWidth: Int) = (row * tableWidth) + column

    @BeforeTest
    abstract fun initView()

    @AfterTest
    fun cleanUp() {
        FxToolkit.setupFixture {
            stage.scene.root = Region()
        }
    }
}