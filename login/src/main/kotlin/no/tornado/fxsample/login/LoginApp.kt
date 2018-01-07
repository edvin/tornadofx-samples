package no.tornado.fxsample.login

import javafx.application.Application
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class LoginApp : App(LoginScreen::class) {
    val loginController: LoginController by inject()

    override fun start(stage: Stage) {
        importStylesheet(Styles::class)
        super.start(stage)
        loginController.init()
    }
}

fun main(args: Array<String>) {
    Application.launch(LoginApp::class.java, *args)
}