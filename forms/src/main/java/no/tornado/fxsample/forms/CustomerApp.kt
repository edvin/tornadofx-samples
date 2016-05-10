package no.tornado.fxsample.forms

import tornadofx.App
import tornadofx.importStylesheet

class CustomerApp : App() {
    override val primaryView = CustomerForm::class

    init {
        importStylesheet(Styles::class)
    }
}