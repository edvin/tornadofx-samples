package no.tornadofx.fxsample.springexample

import org.springframework.stereotype.Component

/**
 * Created by ronsmits on 11/03/2017.
 */

@Component class HelloBean {
    fun helloworld() : String =  data

    companion object {
        const val data = "Hello by di()"
    }
}