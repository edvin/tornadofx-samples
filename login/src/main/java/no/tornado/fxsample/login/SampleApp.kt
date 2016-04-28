package no.tornado.fxsample.login

import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class SampleApp : App() {
    override val primaryView = Workbench::class
    val loginController: LoginController by inject()

    override fun start(stage: Stage) {
        importStylesheet(Styles::class)
        super.start(stage)
        loginController.init()
    }
}