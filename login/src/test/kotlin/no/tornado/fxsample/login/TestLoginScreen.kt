package no.tornado.fxsample.login

import javafx.scene.Node
import javafx.scene.input.KeyCode
import no.tornado.fxsample.TestBase
import org.testfx.assertions.api.Assertions.assertThat
import kotlin.test.Test

class TestLoginScreen: TestBase() {
    override fun initView() {
        showView<LoginScreen, LoginApp>()
    }

    @Test
    fun testAdminLogin() {
        clickOn(LoginScreen.usernameLabel).write(LoginController.adminLogin).push(KeyCode.ENTER)
        clickOn(LoginScreen.passwordLabel).write(LoginController.adminPassword).push(KeyCode.ENTER)

        assertThat(lookup(SecureScreen.screenTitle).queryAs(Node::class.java)).isVisible

        clickOn(SecureScreen.logoutLabel)
    }
}