package no.tornadofx.fxsamples.withpojo.app

import javafx.application.Application
import no.tornadofx.fxsamples.withpojo.view.ItemViewModelWithPojos
import tornadofx.App

class MyApp : App(ItemViewModelWithPojos::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}
