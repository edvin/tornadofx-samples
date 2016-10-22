package no.tornadofx.fxsamples.withfxproperties

import javafx.application.Application
import no.tornadofx.fxsamples.withfxproperties.views.ItemViewModelWithFxMainView
import tornadofx.App

class WithFXPropertiesApp : App(ItemViewModelWithFxMainView::class)


fun main(args: Array<String>) {
    Application.launch(WithFXPropertiesApp::class.java, *args)
}