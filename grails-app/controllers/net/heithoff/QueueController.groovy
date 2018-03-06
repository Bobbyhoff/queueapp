package net.heithoff

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QueueController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Queue.list(params), model:[queueCount: Queue.count()]
    }

    def show(Queue queue) {
        respond queue
    }

    def create() {
        respond new Queue(params)
    }

    @Transactional
    def save(Queue queue) {
        if (queue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (queue.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond queue.errors, view:'create'
            return
        }

        queue.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'queue.label', default: 'Queue'), queue.id])
                redirect queue
            }
            '*' { respond queue, [status: CREATED] }
        }
    }

    def edit(Queue queue) {
        respond queue
    }

    @Transactional
    def update(Queue queue) {
        if (queue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (queue.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond queue.errors, view:'edit'
            return
        }

        queue.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'queue.label', default: 'Queue'), queue.id])
                redirect queue
            }
            '*'{ respond queue, [status: OK] }
        }
    }

    @Transactional
    def delete(Queue queue) {

        if (queue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        queue.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'queue.label', default: 'Queue'), queue.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'queue.label', default: 'Queue'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
