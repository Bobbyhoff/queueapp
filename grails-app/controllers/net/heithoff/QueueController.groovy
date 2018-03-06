package net.heithoff

class QueueController {

    def index() { }

    def lobby() {
        def queueList = Queue.findAll()

        [queueList: queueList, queueCount: queueList.size()]
    }

}
