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

    inline fun <reified T: View> showView() {
        testedView = find<T>()

        FxToolkit.setupFixture {
            stage.scene = Scene(testedView.root)
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