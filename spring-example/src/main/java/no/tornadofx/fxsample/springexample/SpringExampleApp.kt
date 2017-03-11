package no.tornadofx.fxsample.springexample

import javafx.application.Application
import org.springframework.context.support.ClassPathXmlApplicationContext
import tornadofx.*
import kotlin.reflect.KClass

/**
 * Created by ronsmits on 11/03/2017.
 */

class SpringExampleApp : App(SpringExampleView::class) {
    init {
        val springContext = ClassPathXmlApplicationContext("beans.xml")
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = springContext.getBean(type.java)
        }
    }
}


fun main(args: Array<String>) {
    Application.launch(SpringExampleApp::class.java, *args)
}