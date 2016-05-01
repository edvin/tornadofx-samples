package no.tornado.fxsample.login

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.scene.control.CheckBox
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.util.Duration
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

    fun shakeStage() {
        var x = 0
        var y = 0
        val cycleCount = 10
        val move = 10
        val keyframeDuration = Duration.seconds(0.04)

        val stage = FX.primaryStage

        val timelineX = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (x == 0) {
                stage.x = stage.x + move
                x = 1
            } else {
                stage.x = stage.x - move
                x = 0
            }
        }))

        timelineX.cycleCount = cycleCount
        timelineX.isAutoReverse = false

        val timelineY = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (y == 0) {
                stage.y = stage.y + move
                y = 1;
            } else {
                stage.y = stage.y - move
                y = 0;
            }
        }))

        timelineY.cycleCount = cycleCount;
        timelineY.isAutoReverse = false;

        timelineX.play()
        timelineY.play();
    }
}
