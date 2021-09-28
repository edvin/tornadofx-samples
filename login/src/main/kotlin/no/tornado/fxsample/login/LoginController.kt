package no.tornado.fxsample.login

import tornadofx.*

class LoginController : Controller() {
    val loginScreen: LoginScreen by inject()
    val secureScreen: SecureScreen by inject()

    fun init() {
        with(config) {
            if (containsKey(USERNAME) && containsKey(PASSWORD))
                tryLogin(USERNAME, PASSWORD, true)
            else
                showLoginScreen("Please log in")
        }
    }

    fun showLoginScreen(message: String, shake: Boolean = false) {
        secureScreen.replaceWith(loginScreen, sizeToScene = true, centerOnScreen = true)
        runLater {
            if (shake) loginScreen.shakeStage()
        }
    }

    fun showSecureScreen() {
        loginScreen.replaceWith(secureScreen, sizeToScene = true, centerOnScreen = true)
    }

    fun tryLogin(username: String, password: String, remember: Boolean) {
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->

            if (successfulLogin) {
                loginScreen.clear()

                if (remember) {
                    with(config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }

                showSecureScreen()
            } else {
                showLoginScreen("Login failed. Please try again.", true)
            }
        }
    }

    fun logout() {
        with(config) {
            remove(USERNAME)
            remove(PASSWORD)
            save()
        }

        showLoginScreen("Log in as another user")
    }

    companion object {
        val USERNAME = "username"
        val PASSWORD = "password"
    }

}