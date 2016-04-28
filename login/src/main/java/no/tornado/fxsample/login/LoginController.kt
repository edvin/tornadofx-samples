package no.tornado.fxsample.login

import javafx.application.Platform
import tornadofx.Controller
import tornadofx.FX

class LoginController : Controller() {
    val loginScreen: LoginScreen by inject()

    fun init() {
        FX.primaryStage.hide()

        with (config) {
            if (containsKey(USERNAME) && containsKey(PASSWORD))
                tryLogin(string(USERNAME), string(PASSWORD), true)
            else
                showLoginScreen("Please log in")
        }
    }

    fun showLoginScreen(message: String) {
        FX.primaryStage.hide()
        loginScreen.title = message
        loginScreen.openModal()
        Platform.runLater { loginScreen.username.requestFocus() }
    }

    fun showWorkbench() {
        loginScreen.closeModal()
        FX.primaryStage.show()
    }

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->

            if (successfulLogin) {
                loginScreen.clear()

                if (remember) {
                    with (config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }

                showWorkbench()
            } else {
                showLoginScreen("Login failed. Please try again.")
            }
        }
    }

    fun logout() {

        with (config) {
            remove(USERNAME)
            remove(PASSWORD)
            save()
        }

        FX.primaryStage.hide()
        showLoginScreen("Log in as another user")
    }

    companion object {
        val USERNAME = "username"
        val PASSWORD = "password"
    }

}