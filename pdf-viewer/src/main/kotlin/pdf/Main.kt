import com.sun.deploy.util.BlackList
import javafx.application.Application
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Pos
import javafx.scene.effect.BlurType
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import tornadofx.*
import java.io.File

class PdfViewerApp : App(PdfViewer::class) {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(PdfViewerApp::class.java, *args)
        }

    }

}

class PdfViewer: View("Pdf Viewer") {
    val documentProperty = SimpleObjectProperty<PDDocument>()
    var document by documentProperty
    val currentPageProperty = SimpleObjectProperty<Image>(WritableImage(1,1))
    var currentPage by currentPageProperty
    val currentPageNumberProperty = SimpleIntegerProperty(0)
    var currentPageNumber by currentPageNumberProperty
    val pageCountProperty = SimpleIntegerProperty(0)
    var pageCount by pageCountProperty
    val scaleProperty = SimpleFloatProperty(1f)
    var scale by scaleProperty
    var pdfRenderer : PDFRenderer? = null


    override val root = borderpane {
        top = hbox {
            style {
                alignment = Pos.CENTER
                padding = box(20.px)
            }
                button("|<") {
                    action {
                        currentPageNumberProperty.value = 0
                    }
                }
                button("<") {
                    action {
                        currentPageNumberProperty.value--
                    }
                }
                textfield (currentPageNumberProperty + 1)
                label(pageCountProperty)
                button(">") {
                    action {
                        currentPageNumberProperty.value++
                    }
                }
                button(">|"){
                    action {
                        currentPageNumberProperty.value = Math.max(pageCountProperty.value - 1, 0)
                    }
                }
        }
        center = scrollpane {
            style {
                padding = box(0.px, 60.px, 0.px, 60.px)
                backgroundColor += Color.DARKGRAY
                effect = InnerShadow(BlurType.THREE_PASS_BOX, Color.GRAY, 10.0, 10.0,10.0,10.0 )

            }
            vbox {
                imageview {
                    currentPageProperty.onChange {
                        this.image = currentPage
                        this.autosize()
                    }
                }
            }
        }
    }

    init {
        document = PDDocument.load(File("/home/mcarboni/digitalsignatures20130304.pdf"))
        pdfRenderer = PDFRenderer(document)
        pageCountProperty.value = document?.pages?.count

        currentPageNumberProperty.onChange { n -> openPage(n) }
        openPage(0)
    }

    private fun openPage(pageCounter: Int) {
        val bim = pdfRenderer?.renderImage(pageCounter, scale)//pdfRenderer?.renderImageWithDPI(pageCounter, 300)
        if (bim != null) {
            val writableImage = WritableImage(bim.width, bim.height)
            currentPage = SwingFXUtils.toFXImage(bim, null)
        }
    }
}