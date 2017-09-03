package no.tornadofx.fxsample.springexample

import org.springframework.stereotype.Component
import kotlin.reflect.jvm.internal.impl.javax.inject.Singleton

/**
 * Created by ronsmits on 11/03/2017.
 */

@Component class HelloBean {
    fun helloworld() : String =  "Hello by di()"
}