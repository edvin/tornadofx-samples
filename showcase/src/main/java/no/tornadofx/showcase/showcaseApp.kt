package no.tornadofx.showcase

import javafx.application.Application
import javafx.scene.control.TabPane
import no.tornado.fxsample.charts.chartview
import no.tornado.fxsample.forms.CustomerForm
import no.tornado.fxsample.treeviews.DemoTreeViews
import no.tornadofx.fxsamples.tableviews.DemoTableView
import no.tornadofx.fxsamples.withfxproperties.views.ItemViewModelWithFxMainView
import no.tornadofx.fxsamples.withpojo.view.itemViewModelWithPojos
import tornadofx.*


class showcaseApp : App(Mainview::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(showcaseApp::class.java, *args)
}

class Mainview : View("Show all examples") {

    val chartview: chartview by inject()
    val customerform: CustomerForm by inject()
    val withFxProperties: ItemViewModelWithFxMainView by inject()
    val withpojos: itemViewModelWithPojos by inject()
    val tableView: DemoTableView by inject()
    val treeviews: DemoTreeViews by inject()
    override val root = tabpane {
        addClass(Styles.mainview)
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("Charts") { this += chartview.root }
        tab("Forms") { this += customerform.root }
        tab("itemviewModel") {
            hbox {
                this@hbox += withFxProperties.root
                this@hbox += withpojos.root
            }
        }
        tab("tableviews") {
            this += tableView.root
        }
        tab("treeviews") {
            this += treeviews.root
        }
    }
}