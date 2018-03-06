package helloworld

import grails.util.Environment
import net.heithoff.Queue

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {
            (0..1000).each {
                Queue queue = new Queue(firstName: "Robert", lastName: "heithoff",contact: "jessica").save()
            }
//            Queue queue = new Queue(firstName: "Robert", lastName: "heithoff",contact: "jessica").save()
            Queue two = new Queue(firstName: "Robert", lastName: "smith",contact: "jessica").save()

//            println queue.toString()

            def robert = Queue.findByFirstNameAndLastName("Robert", "heithoff")
            List<Queue> test = Queue.findAllByFirstNameIlike("robert")
        }
    }
    def destroy = {
    }
}
