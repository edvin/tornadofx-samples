package no.tornado.fxsample.login

import javafx.scene.control.CheckBox
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import no.tornado.fxsample.login.Styles.Companion.loginScreen
import tornadofx.*

class LoginScreen : View() {
    override val root = GridPane()
    val loginController: LoginController by inject()

    var username: TextField by singleAssign()
    var password: PasswordField by singleAssign()
    var remember: CheckBox by singleAssign()

    init {
        title = "Please log in"

        with (root) {
            addClass(loginScreen)

            row("Username") {
                username = textfield()
            }

            row("Password") {
                password = passwordfield()
            }

            row("Remember me") {
                remember = checkbox()
            }

            row {
                button("Login") {
                    isDefaultButton = true

                    setOnAction {
                        loginController.tryLogin(
                                username.text,
                                password.text,
                                remember.isSelected
                        )
                    }
                }
            }

        }
    }

    fun clear() {
        username.clear()
        password.clear()
        remember.isSelected = false
    }
}
