package helloworld

import grails.util.Environment
import net.heithoff.Queue

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {
            Queue queue = new Queue(firstName: "Robert", lastName: "heithoff",contact: "jessica").save()

            println queue.toString()
        }
    }
    def destroy = {
    }
}
