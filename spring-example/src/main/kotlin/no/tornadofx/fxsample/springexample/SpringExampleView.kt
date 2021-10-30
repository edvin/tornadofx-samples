package no.tornadofx.fxsample.springexample

import tornadofx.*

class SpringExampleView : View() {
    private val bean : HelloBean by di()
    override val root = vbox {
        label(bean.helloworld()).paddingAll=20
    }
}