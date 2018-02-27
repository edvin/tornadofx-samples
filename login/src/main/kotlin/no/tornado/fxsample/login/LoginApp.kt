package no.tornado.fxsample.login

import javafx.stage.Stage
import tornadofx.*

class LoginApp : App(LoginScreen::class, Styles::class) {
    val loginController: LoginController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        loginController.init()
    }
}

fun main(args: Array<String>) {
    launch<LoginApp>(args)
}