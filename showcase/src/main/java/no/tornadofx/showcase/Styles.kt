package no.tornadofx.showcase

import tornadofx.*

class Styles : Stylesheet(){
    companion object {
        val mainview by cssclass()
    }

    init {
        select(mainview) {
            prefWidth = 1024.px
            prefHeight = 600.px
        }
    }
}