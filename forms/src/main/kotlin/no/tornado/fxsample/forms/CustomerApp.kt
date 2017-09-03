package no.tornado.fxsample.forms

import javafx.application.Application
import tornadofx.App
import tornadofx.importStylesheet

class CustomerApp : App() {
    override val primaryView = CustomerForm::class

    init {
        importStylesheet(Styles::class)
    }
}

fun main(args: Array<String>) {
    Application.launch(CustomerApp::class.java, *args)
}