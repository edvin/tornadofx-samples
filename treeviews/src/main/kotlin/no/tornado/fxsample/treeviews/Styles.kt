package no.tornado.fxsample.treeviews

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val wrapper by cssclass()
    }

    init {
        s(wrapper) {
            padding = box(10.px)
            spacing = 10.px
        }
    }
}