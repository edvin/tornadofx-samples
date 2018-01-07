package no.tornado.fxsample.workspace

import javafx.scene.paint.Color
import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.px

/**
 * Created by miguelius on 04/09/2017.
 */
class Styles : Stylesheet() {
    companion object {
        val wrapper by cssclass()
        val consola by cssclass()
    }

    init {
        root {
            prefHeight = 600.px
            prefWidth = 800.px
        }
        textArea and consola {
            baseColor= Color.BLACK
            fontFamily = "Consolas"
            textFill = Color.LIGHTGRAY
        }
    }
}
