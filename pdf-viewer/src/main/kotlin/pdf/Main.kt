import javafx.application.Application
import javafx.beans.property.*
import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Pos
import javafx.scene.effect.BlurType
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import tornadofx.*
import java.io.FileInputStream
import java.io.InputStream
import java.net.URI


class PdfViewerApp : App(PdfViewer::class) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<PdfViewerApp>(args)
        }
    }

    override fun onBeforeShow(view: UIComponent) {
        (view as PdfViewer).uriString(resources.url("/pdf/tornadofx-guide.pdf").toString())
    }
}

class PdfViewModel : ViewModel() {
    var documentURIString = SimpleStringProperty("")
    var documentURI = SimpleObjectProperty<URI>(URI(""))
    var documentInputStream = SimpleObjectProperty<InputStream>()
    var document: PDDocument? = null
    var pdfRenderer: PDFRenderer? = null
    val currentPage = SimpleObjectProperty<Image>(WritableImage(1, 1))
    val currentPageNumber = SimpleIntegerProperty(0)
    val pageCount = SimpleIntegerProperty(0)
    val scale = SimpleFloatProperty(1f)

    init {
        documentInputStream.onChange { input ->
            if (input is InputStream) {
                document = PDDocument.load(input)
                pdfRenderer = PDFRenderer(document)
                pageCount.value = document?.pages?.count
                openPage(0)
            }
        }
        documentURIString.onChange { documentURI.value = URI(it) }
        documentURI.onChange { nuevaUri ->
            val input = when (nuevaUri!!.scheme) {
                "file" -> FileInputStream(nuevaUri.toURL().file)
                "http", "https" -> {
                    val client = HttpClientBuilder.create().build()
                    val request = HttpGet(nuevaUri)
                    val response = client.execute(request)
                    response.entity.content
                }
                else -> null
            }
            documentInputStream.value = input
        }
        currentPageNumber.onChange { n ->
            openPage(n)
            log.info("Cambio a pagina ${n}")
        }
    }

    fun openPage(pageCounter: Int) {
        val bim = pdfRenderer?.renderImage(pageCounter, scale.value)//pdfRenderer?.renderImageWithDPI(pageCounter, 300)
        if (bim != null) {
            currentPage.value = SwingFXUtils.toFXImage(bim, null)
        }
    }

    fun firstPage() {
        currentPageNumber.value = 0
    }

    fun previousPage() {
        --currentPageNumber.value
    }

    fun nextPage() {
        ++currentPageNumber.value
    }

    fun lastPage() {
        currentPageNumber.value = pageCount.value - 1
    }

    var isFirst = currentPageNumber.isEqualTo(0)

    var isLast = currentPageNumber.isEqualTo(pageCount - 1)
}

class PdfViewer : Fragment("Pdf Viewer") {
    val pdfModel: PdfViewModel by inject()

    override val root = borderpane {
        top = hbox(spacing = 10) {
            alignment = Pos.CENTER
            paddingAll = 20
            button("|<") {
                action(pdfModel::firstPage)
                disableWhen(pdfModel.isFirst)
            }
            button("<") {
                action(pdfModel::previousPage)
                disableWhen(pdfModel.isFirst)
            }
            textfield(pdfModel.currentPageNumber + 1)
            label(pdfModel.pageCount)
            button(">") {
                action(pdfModel::nextPage)
                disableWhen(pdfModel.isLast)
            }
            button(">|") {
                action(pdfModel::lastPage)
                disableWhen(pdfModel.isLast)
            }
        }
        center {
            scrollpane {
                style {
                    padding = box(0.px, 60.px, 0.px, 60.px)
                    backgroundColor += Color.DARKGRAY
                    effect = InnerShadow(BlurType.THREE_PASS_BOX, Color.GRAY, 10.0, 10.0, 10.0, 10.0)
                }
                imageview(pdfModel.currentPage) {
                    minWidthProperty().bind(this@scrollpane.widthProperty())
                }
            }
        }
    }

    fun uriString(uri: String) {
        pdfModel.documentURIString.value = uri
    }

}