package no.tornado.fxsample.forms

import javafx.application.Application
import tornadofx.App

class CustomerApp : App(CustomerForm::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(CustomerApp::class.java, *args)
}