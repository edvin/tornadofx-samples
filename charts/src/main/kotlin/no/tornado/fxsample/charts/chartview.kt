package no.tornado.fxsample.charts

import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.layout.GridPane
import tornadofx.*

/**
 * Created by ronsmits on 01/05/16.
 */
class chartview : View("Charts") {
    override val root = GridPane()

    init {
        with (root) {
            row() {
                piechart ("Imported Fruits") {
                    data("Grapefruit", 12.0)
                    data("Oranges", 25.0)
                    data("Plums", 10.0)
                    data("Pears", 22.0)
                    data("Apples", 30.0)
                }
                barchart("Stock Monitoring, 2010", CategoryAxis(), NumberAxis()) {
                    series("Portfolio 1") {
                        data("Jan", 23)
                        data("Feb", 14)
                        data("Mar", 15)
                    }
                    series("Portfolio 2") {
                        data("Jan", 11)
                        data("Feb", 19)
                        data("Mar", 27)
                    }
                }
                stackedbarchart("Stock again", CategoryAxis(), NumberAxis()) {
                    series("Portfolio 1") {
                        data("Jan", 23)
                        data("Feb", 14)
                        data("Mar", 15)
                    }
                    series("Portfolio 2") {
                        data("Jan", 11)
                        data("Feb", 19)
                        data("Mar", 27)
                    }
                }
                linechart("linechart", CategoryAxis(), NumberAxis()) {
                    series("month") {
                        data("jan", 10)
                        data("feb", 20)
                        data("mar", 5)
                    }
                    series("week") {
                        data("jan", 1)
                        data("feb", 2)
                    }
                }
            }
            row {

                barchart("multiseries", CategoryAxis(), NumberAxis()) {
                    multiseries("Portfolio 1", "Portfolio 2") {
                        data("Jan", 23, 10)
                        data("Feb", 14, 5)
                        data("Mar", 15, 8)
                    }
                }
                bubblechart("bubblechart", NumberAxis(), NumberAxis()) {
                    series("series 1") {
                        data(1, 1, 1)
                        data(5, 5, 0.25)
                    }
                }
                areachart("area chart", CategoryAxis(), NumberAxis()) {
                    series("area 1") {
                        data("Jan", 10)
                        data("Feb", 5)
                        data("Mar", 8)
                    }
                    series("area 2") {
                        data("Jan", 0.5)
                        data("Feb", 3.25)
                        data("Mar", 6.75)
                    }
                }

                scatterchart("scattered", CategoryAxis(), NumberAxis()) {
                    series("scatter 1") {
                        data("jan", 5)
                        data("feb", 9)
                    }
                    series("scatter 2") {
                        data("jan", 6)
                        data("feb", .05)
                        data("mar", 11)
                    }
                }
            }
        }
    }
}
